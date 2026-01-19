package com.corelabs.resume.service;

import com.corelabs.resume.mapper.ResumeMapper;
import com.corelabs.resume.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    final ResumeMapper resumeMapper;

    public ResumeService(ResumeMapper resumeMapper) { this.resumeMapper = resumeMapper; }

    public List<ResumeVO> selectResumeList(ResumeVO resumeVO) {
        return resumeMapper.selectResumeList();
    }

    public List<ResumeVO> selectResumeList() {
        return resumeMapper.selectResumeList();
    }

    public List<ResumeVO> selectResumeList(ResumeSearchVO resumeSearchVO) {
        return resumeMapper.selectResumeList(resumeSearchVO);
    }

    public ResumeVO selectResumeById(String resumeId) {
        return resumeMapper.selectResumeById(resumeId);
    }

    @Transactional
    public int saveResume(ResumeVO resumeVO) {
        int resultCnt = 0;

        String uuid = UUID.randomUUID().toString();
        resumeVO.setResumeId(uuid);

        resultCnt = resumeMapper.insertResume(resumeVO);

        insertEducation(resumeVO, uuid);
        insertCareer(resumeVO, uuid);
        insertLicense(resumeVO, uuid);
        insertSkill(resumeVO, uuid);

        return resultCnt;
    }

    @Transactional
    public int updateResume(ResumeVO resumeVO) {
        int resultCnt = 0;

        resultCnt = resumeMapper.updateResume(resumeVO);

        String resumeId = resumeVO.getResumeId();
        deleteRelatedData(resumeId);

        insertEducation(resumeVO, resumeId);
        insertCareer(resumeVO, resumeId);
        insertLicense(resumeVO, resumeId);
        insertSkill(resumeVO, resumeId);

        return resultCnt;
    }

    public void deleteResume(String resumeId) {
        resumeMapper.deleteSkill(resumeId);
        resumeMapper.deleteLicense(resumeId);
        resumeMapper.deleteCareer(resumeId);
        resumeMapper.deleteEducation(resumeId);
        resumeMapper.deleteResume(resumeId);
    }

    private void deleteRelatedData(String resumeId) {
        resumeMapper.deleteSkill(resumeId);
        resumeMapper.deleteLicense(resumeId);
        resumeMapper.deleteCareer(resumeId);
        resumeMapper.deleteEducation(resumeId);
    }

    private void insertEducation(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getEducationList() != null && !resumeVO.getEducationList().isEmpty()) {
            for (ResumeEducationVO resumeEducationVO : resumeVO.getEducationList()) {
                resumeEducationVO.setResumeId(resumeId);
                resumeMapper.insertEducation(resumeEducationVO);
            }
        }
    }

    private void insertCareer(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getCareerList() != null && !resumeVO.getCareerList().isEmpty()) {
            for (ResumeCareerVO resumeCareerVO : resumeVO.getCareerList()) {
                resumeCareerVO.setResumeId(resumeId);
                resumeMapper.insertCareer(resumeCareerVO);
            }
        }
    }

    private void insertLicense(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getLicenseList() != null && !resumeVO.getLicenseList().isEmpty()) {
            for (ResumeLicenseVO resumeLicenseVO : resumeVO.getLicenseList()) {
                resumeLicenseVO.setResumeId(resumeId);
                resumeMapper.insertLicense(resumeLicenseVO);
            }
        }
    }

    private void insertSkill(ResumeVO resumeVO, String resumeId) {
        if (resumeVO.getSkillList() != null && !resumeVO.getSkillList().isEmpty()) {
            for (ResumeSkillVO resumeSkillVO : resumeVO.getSkillList()) {
                resumeSkillVO.setResumeId(resumeId);
                resumeMapper.insertSkill(resumeSkillVO);
            }
        }
    }

    public List<Map<String, Object>> selectResumeListRaw(ResumeSearchVO resumeSearchVO) {

        List<ResumeFlatVO> rawList = resumeMapper.selectResumeListRaw(resumeSearchVO);
        Map<String, List<ResumeFlatVO>> groupedMap = rawList.stream()
                .collect(Collectors.groupingBy(ResumeFlatVO::getResumeId, LinkedHashMap::new, Collectors.toList()));

        return groupedMap.values().stream().map(list -> {
            ResumeFlatVO firstItem = list.get(0);
            Map<String, Object> row = new HashMap<>();

            row.put("resumeId", firstItem.getResumeId());
            row.put("name", firstItem.getName());
            row.put("military", firstItem.getMilitary());
            row.put("marriage", firstItem.getMarriage());
            row.put("address", firstItem.getAddress());
            row.put("createdTime", firstItem.getCreatedTime());
            row.put("updatedTime", firstItem.getUpdatedTime());

            String finalEducation = findFinalEducationByDate(list);
            row.put("finalEducation", finalEducation);

            // 1. 나이 계산
            if (firstItem.getBirth() != null) {
                int age = Period.between(firstItem.getBirth().toLocalDate(), LocalDate.now()).getYears() + 1;
                String ageToString = age + "세";
                row.put("age", ageToString);
            }

            // 2. 총 경력 기간 합산 로직
            // 중복된 조인 레코드에서 고유한 경력(날짜쌍)만 추출
            long totalMonths = list.stream()
                    .filter(item -> item.getCompanyStartTerm() != null)
                    .map(item -> new AbstractMap.SimpleEntry<>(item.getCompanyStartTerm(), item.getCompanyEndTerm()))
                    .distinct() // 중복된 경력 데이터 제거
                    .mapToLong(entry -> {
                        LocalDate start = entry.getKey().toLocalDate();
                        LocalDate end = (entry.getValue() == null) ? LocalDate.now() : entry.getValue().toLocalDate();
                        return ChronoUnit.MONTHS.between(start, end);
                    }).sum();

            row.put("totalExperience", formatExperience(totalMonths));

            // 자격증 및 기술 요약 (이전 코드 유지)
            List<String> license = list.stream().map(ResumeFlatVO::getLicenseName).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            row.put("licenseSummary", createLicenseSummary(license));

            List<String> skills = list.stream().map(ResumeFlatVO::getProjectLanguage).filter(Objects::nonNull).distinct().collect(Collectors.toList());
            row.put("topSkill", findTopSkill(skills));

            return row;
        }).collect(Collectors.toList());
    }

    private String createLicenseSummary(List<String> licenses) {
        if (licenses.isEmpty()) return "자격증 없음";

        // 우선순위: 기사 > SQL > 기타
        String rep = licenses.stream().filter(s -> s.contains("기사")).findFirst()
                .orElseGet(() -> licenses.stream().filter(s -> s.toUpperCase().contains("SQL")).findFirst()
                        .orElse(licenses.get(0)));

        int extraCount = licenses.size() - 1;
        return extraCount > 0 ? rep + " 외 " + extraCount + "종" : rep;
    }

    private String findTopSkill(List<String> skills) {

        // 리스트 자체가 null이거나 비어있으면 즉시 반환
        if (skills == null || skills.isEmpty()) return "데이터 없음";

        Map<String, Long> counts = skills.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        if (counts.isEmpty()) return "데이터 없음";

        return counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("데이터 없음");

    }

    // 개월 수를 "n년 m개월" 형식으로 변환
    private String formatExperience(long months) {
        if (months <= 0) return "신입";
        long years = months / 12;
        long remainingMonths = months % 12;

        if (years == 0) return remainingMonths + "개월";
        if (remainingMonths == 0) return years + "년";
        return years + "년 " + remainingMonths + "개월";
    }

    private String findFinalEducationByDate(List<ResumeFlatVO> list) {
        return list.stream()
                .filter(item -> item.getSchoolName() != null && item.getSchoolDate() != null)
                .distinct()
                // 졸업일자가 가장 큰(최근인) 항목을 찾음
                .max(Comparator.comparing(ResumeFlatVO::getSchoolDate))
                .map(item -> item.getSchoolName() + "(" + item.getSchoolStatus() + ")")
                .orElse("학력 정보 없음");
    }

}