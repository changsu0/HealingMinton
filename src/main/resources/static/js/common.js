let JS_COMMON = {
    callAjaxForm : function (paramUrl, paramData, paramMethodType, cbFunc, asyncFlag = true){
        $.ajax({
            url: paramUrl,
            type: paramMethodType,
            data: paramData,
            dataType: "json",
            async: asyncFlag,
            //contentType:"application/json", //이거 쓰면 Form post로 갈때 파라미터가 안날라감 response payload
            success: function( data ){
                cbFunc(data);
            },
            error: function(xhr, status, error){
                alert(xhr.responseText);
            },
            complete: function(xhr, status){}
        });
    }

    , callAjaxJson : function (paramUrl, paramData, paramMethodType, cbFunc, asyncFlag = true){
        $.ajax({
            url: paramUrl,
            type: paramMethodType,
            data: JSON.stringify( paramData ),
            dataType: "json",
            async: asyncFlag,
            contentType:"application/json", //Json으로 데이터를 보내줄땐 써줘야함
            success: function( data ){
                cbFunc(data);
            },
            error: function(xhr, status, error){
                alert(xhr.responseText);
            },
            complete: function(xhr, status){}
        });
    }

    , formReset : function ( frmID ){
        $('#' + frmID).find('input').val('');
        $('#' + frmID).find('select option').each(function (){
            $(this).prop('selected',false);
        });
        $('#' + frmID).find('select option:eq(0)').prop('selected',true);
    }

    , isEmpty : function ( value ){
        if (value === null || value === undefined) return true;
        if (typeof value === 'string' && value.trim() === '') return true;
        if (Array.isArray(value) && value.length === 0) return true;
        if (typeof value === 'object' && Object.keys(value).length === 0) return true;
        return false;
    }

    , containsValue : (arr, value) => {
        return arr.includes(value);
    }

    // Ajax 호출 시 Form Data를 Json형태로 변경해줌
    , serializeObject : function() {
        var obj = null;
        try {
            if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
                var arr = this.serializeArray();
                if(arr){ obj = {};
                    jQuery.each(arr, function() {
                        obj[this.name] = this.value; });
                }
            }
        }catch(e) {
            alert(e.message);
        }finally {}
        return obj;
    }

    , getTodate : function( dateType ) {
        let today = new Date();

        let year = today.getFullYear(); // 년도
        let month = today.getMonth() + 1;  // 월
        let date = today.getDate();  // 날짜

        let lastDate = new Date(year, month, 0).getDate();  // 마지막 일

        let hours = today.getHours(); // 시
        let minutes = today.getMinutes();  // 분
        let seconds = today.getSeconds();  // 초

        let separatorD = '-';
        let separatorT = ':';

        switch ( dateType ){
            case 'YYYY'     : return year;
            case 'YYYYMM'   : return year + separatorD + this.lpad(month, '2', '0');
            case 'YYYYMMDD' : return year + separatorD + this.lpad(month, '2', '0') + separatorD + this.lpad(date, '2', '0');
            case 'hh'       : return this.lpad(hours, '2', '0');
            case 'hhmm'     : return this.lpad(hours, '2', '0') + separatorT + this.lpad(minutes, '2', '0');
            case 'hhmmss'   : return this.lpad(hours, '2', '0') + separatorT + this.lpad(minutes, '2', '0') + separatorT + this.lpad(seconds, '2', '0');
            case 'FULL'     : return year + separatorD + this.lpad(month, '2', '0') + separatorD + this.lpad(date, '2', '0') + ' ' + this.lpad(hours, '2', '0') + separatorT + this.lpad(minutes, '2', '0') + separatorT + this.lpad(seconds, '2', '0');
            case 'FIRST_DAY': return year + separatorD + this.lpad(month, '2', '0') + separatorD + '01';
            case 'LAST_DAY' : return year + separatorD + this.lpad(month, '2', '0') + separatorD + this.lpad(lastDate, '2', '0');
        }
    }

    , getNumberFormat : function( number ) {
        const option = {
            maximumFractionDigits: 1
        };
        if ( this.isEmpty( number ) )
            return 0;
        else return Number(number).toLocaleString('ko-KR', option);
    }

    , getPhoneNumber : function( phoneNumber ) {
        return phoneNumber
            .replace(/[^0-9]/g, '') // 숫자를 제외한 모든 문자 제거
            .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
    }

    , getChkPhoneNumber : function( phoneNumber ) {
        if( this.isEmpty(phoneNumber)){
            return false;
        }
        let phoneRule = /^(01[016789]{1})[0-9]{3,4}[0-9]{4}$/;
        return phoneRule.test(phoneNumber);
    }

    , lpad : function( str, padLen, padStr ) {
        if (padStr.length > padLen) {
            console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
            return str;
        }
        str += ""; // 문자로
        padStr += ""; // 문자로
        while (str.length < padLen)
            str = padStr + str;
        str = str.length >= padLen ? str.substring(0, padLen) : str;
        return str;
    }

    , rpad : function( str, padLen, padStr ) {
        if (padStr.length > padLen) {
            console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
            return str + "";
        }
        str += ""; // 문자로
        padStr += ""; // 문자로
        while (str.length < padLen)
            str += padStr;
        str = str.length >= padLen ? str.substring(0, padLen) : str;
        return str;
    }

    , getTodayDay : function( ) {
        const days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
        const today = new Date();
        return days[today.getDay()];
    }

    , chkPhoneValidate : function( inputId ) {
        //1. 입력한 value 값을 읽어온다.
        let inputPhone =  $('#' + inputId ).val();
        //2. 유효성(5글자이상 10글자 이하)을 검증한다.
        isValid = JS_COMMON.getChkPhoneNumber(inputPhone);
        //3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가

        if(isValid && inputPhone.length > 0){
            $('#'+inputId).removeClass("is-invalid");
            $('#'+inputId).addClass("is-valid");
        }else{
            $('#'+inputId).removeClass("is-valid");
            $('#'+inputId).addClass("is-invalid");
        }
    }

    , chkRequired : function( inputId ) {
        //1. 입력한 value 값을 읽어온다.
        let inputValue =  $('#' + inputId ).val();
        //3. 유효하다면 input 요소에 is-valid 클래스 추가, 아니라면 is-invalid 클래스 추가
        if(inputValue.length > 0){
            $('#'+inputId).removeClass("is-invalid");
            $('#'+inputId).addClass("is-valid");
            isInputValid = true;
        }else{
            $('#'+inputId).removeClass("is-valid");
            $('#'+inputId).addClass("is-invalid");
            isInputValid = false;
        }
    }
}
Window.JS_COMMON = JS_COMMON;