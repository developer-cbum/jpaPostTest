$(document).ready(function () {

    //이름태그
    const $name = $('#member-name');
    //이메일 주소 태그
    const $email = $('#member-email');
    //비밀번호 태그
    const $password = $('#member-password');
    //비밀번호 확인 태그
    const $checkPassword = $('#check-member-password');
    //로그인 버튼
    const $loginBtn = $('.login-btn');
    //회원가입 버튼
    const $joinBtn = $('.join-btn');

    //통과
    const $passTags = $('.valid-feedback');
    //불통과
    const $notPassTags = $('.invalid-feedback');

    let nameFlag =false;
    let emailFlag =false;
    let passwordFlag = false;

    $loginBtn.on("click", () => {
        location.href = `/members/login`
    })

    //이메일 유효성검사
    $email.on("input", function () {
        emailCheck();
        if(!emailCheck()){
            checkId();
        }
        joinCheck();
    });



    //이름에 특수문자금지
    $name.bind("input", function () {
        re = /[~!@\#$%^&*\()\-=+_']/gi;
        let temp = $name.val();
        if (re.test(temp)) { //특수문자가 포함되면 삭제하여 값으로 다시셋팅
            $name.val(temp.replace(re, ""));
        }

        if($(this).val() != ''){
            $passTags.eq(0).text(`환영합니다`).show();
            $notPassTags.eq(0).hide();
            nameFlag = true;
        }else{
            $notPassTags.eq(0).text("이름을 입력해주세요").show();
            $passTags.eq(0).hide();
            nameFlag=false;
        }
        joinCheck()

    });

    //비밀번호 검사
    $password.on("input", function (){
           if(nullPassword()){
               $passTags.eq(2).text(`비밀번호 입력완료`).show();
               $notPassTags.eq(2).hide();
               passwordFlag=true;
           }else{
               $notPassTags.eq(2).text("비밀번호를 입력해주세요").show();
               $passTags.eq(2).hide();
               passwordFlag=false;
           }
        samePassword(sameCheckPassword());
    })

    //비밀번호 확인
    $checkPassword.on("input", function (){
        if(!nullCheckPassword()) {
            $notPassTags.eq(3).text("비밀번호 확인를 입력해주세요").show();
            $passTags.eq(3).hide();
        }
        samePassword(sameCheckPassword());
    })


    /*ajax 비동기 통신*/
    function checkId(){
        let memberEmail = $email.val()
        $.ajax({
            url: "/members/checkId",
            type: "post",
            data:  {memberEmail : memberEmail},
            success: function (data){
                if(data){
                    $passTags.eq(1).text("멋진 이메일이네요").show();
                    $notPassTags.eq(1).hide();
                    emailFlag =true;
                }else{
                    $notPassTags.eq(1).text("중복된아이디입니다").show();
                    $passTags.eq(1).hide();
                    emailFlag =false;
                }
            }

        })
    }


    function sameCheckPassword(){
        if($password.val() != $checkPassword.val()){
            return false;
        }
        return true;
    }

    function samePassword(callback){
        if(callback){
            $passTags.eq(3).text(`비밀번호가 일치합니다`).show();
            $notPassTags.eq(3).hide();
            passwordFlag=true;
        }else{
            $notPassTags.eq(3).text("비밀번호 불일치합니다").show();
            $passTags.eq(3).hide();
            passwordFlag=false;
        }

        joinCheck()
    }

    function nullCheckPassword(){
        if($checkPassword.val() ==''){
            return false;
        }
        return true;
    }

    function nullPassword(){
        if($password.val() ==''){
            return false;
        }
        return true;
    }



    /*이메일 유효성검사 함수*/
    function fn_emailChk(email) {
        let regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{1,4}$/;
        if (!regExp.test(email)) {
            return false;
        }
        return true;
    }

    function emailCheck(){
        if (!fn_emailChk($email.val())) {
            $notPassTags.eq(1).text("올바른 이메일 형식을 입력해 주세요").show();
            $passTags.eq(1).hide();
            return true;
        }
        return false;
    }

    function joinCheck(){
        console.log(nameFlag)
        console.log(emailFlag)
        console.log(passwordFlag)
        console.log(nullCheckPassword())
        console.log(nullPassword())

        if(nameFlag && emailFlag && passwordFlag &&nullCheckPassword() &&nullPassword()){
        $joinBtn.prop("disabled", false);
     }else{
            $joinBtn.prop("disabled", true);
        }
}

})