package com.corelabs.sample.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("sampleVO")
public class SampleVO{
	private String sample;
}
