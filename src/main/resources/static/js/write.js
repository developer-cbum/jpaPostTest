$(document).ready(function () {
    //form 태그
    const $form = $('#post-form');
    //버튼 가져오기
    const $registerBtn = $('.register-btn');
    // 입력 태그들
    const $tags = $('.form-control')
    // textarea 게시물 내용 태그
    const $contentBox = $('.content-area');
    // 파일 삭제버튼
    const $fileDeleteBtn = $('.file-delete-btn');
    //파일 인풋
    const $fileInput = $('#file');
    //기존파일 목록
    const $basicFile = $('.rounded');
    //파일 전체를 감싸는 div 태그
    const $filePlusWrap = $('.file-plus-wrap');


    let plusFileDeleteCheck=false;

    //새로 파일 추가 카운트
    let number = 0;
    //삭제 카운트
    let count = 0;
    let sizes = [];
    let name = [];
    // 새파일
    let text = "";
    // 화면에 추가하기 위한 변수선언
    let plusText = "";
    // 파일 인풋


    
    $fileInput.on("change", function () {
        //동적으로 생긴 파일 목록 태그 모음
        const $plusFileContainer = $('.plus-file-container');
        // 파일 업로드 수정할 때 마다 목록 최신화
        $plusFileContainer.remove();
        plusText =""

        //파일 이름 담는 배열 새로 파일이 담길 때마다 초기화
        name = [];
        sizes = [];
        // input hidden 파일 수정할 때마다 초기화
        text = "";

        // 다음 파일들
        let files = $(this)[0].files;
        let formData = new FormData();

        //경로 생성을 위한 yy/mm/dd 설정
        let now = new Date();
        let year = now.getFullYear();
        let month = now.getMonth() + 1;
        let date = now.getDate();

        month = month < 10 ? "0" + month : month;
        date = date < 10 ? "0" + date : date;

        let filePath = year + "/" + month + "/" + date

        //들어온 파일 개수 만큼 반복 후 동적 태그 생성(수정 시 추가 파일도 포함)
        $(files).each((i, file) => {
            formData.append("uploadFile", file);
            sizes.push(files[i].size);
            name.push(files[i].name);

            // ajax로 통신하기 전에 alert 띄우고 막기
            if(sizes[i] > 41943040){
                alert("파일 사이즈가 너무 큽니다.")
                return false;
            }

            text += `
            <input type="hidden" name="files[${i}].filePath" value="${filePath}">
            <input type="hidden" name="files[${i}].fileName" value="${name[i]}">
            <input type="hidden" name="files[${i}].fileSize" value="${sizes[i]}">
             `

            // 화면상에만 추가되어 보이기
            plusText += `
                                <div id="${number}" class="plus-file-container modify-file-wrap">
                                     <div class="file-wrap">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" style="cursor:default"
                                             class="bi bi-file-earmark-arrow-down" viewBox="0 0 16 16">
                                            <path d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293V6.5z"/>
                                            <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>
                                        </svg>
                                        <a class="p-1 rounded" style="color:#0d6efd; font-size: 14px; cursor: default"
                                           href="">${name[i]}
                                        </a><span style="font-size: 12px;cursor:default">(${Math.ceil(sizes[i] / 1024).toFixed(2)}KB)</span><br>
                                    </div>
                                    <button type="button" class="btn btn-primary file-delete-btn active-file-delete-btn">삭제</button>
                                </div>
            `
            number++
        })

        // 아래 파일 정보 input 태그 생성 막고 input 안에 value 초기화
        for (let i = 0; i < sizes.length; i++) {
            if(sizes[i] > 41943040){
                $(this).val("");
                return;
            }
        }

        //수정 시 추가한 파일 목록에 추가해주기
        $filePlusWrap.append(plusText);

        // 서버에 파일 저장하면서 db에 insert시켜줄 uuid들 값도 받아오기
        $.ajax({
            url        : "/files/upload",
            type       : "post",
            async: false,
            data       : formData,
            contentType: false,
            processData: false,
            success    : function (uuids) {
                for (let i = 0; i < uuids.length; i++) {
                    text += `<input type="hidden" name="files[${i}].fileUuid" value="${uuids[i]}">`
                }
            },
            error      : function () {

                alert("예상치 못한 오류 발생입니다.")
            }

        })

    })

    // 게시글 등록 및 수정하기 버튼 클릭
    $registerBtn.on("click", function () {
        if ($tags.eq(0).val() == '') {
            alert("제목을 입력해주세요.")
            $tags.eq(0).focus();
            return;
        }


        if ($contentBox.val() == '') {
            alert("내용을 입력해주세요.")
            $contentBox.focus();
            return;
        }



        //수정창에서 파일을 추가로 올리고 삭제할 때, 아래 코드 실행
        //flag 를 주어 수정하기 버튼을 눌렀을 때 최종 파일 목록에 있는 것을 input hidden 태그로 다시 생성 시켜서 db에 저장

        if(plusFileDeleteCheck){
            plusFileDeleteCheck= false;
            text="";
           if(plusText != "") {$fileInput.trigger("change")};
        }


        // 입력폼에 input hidden 파일정보가 담긴 태그들 붙히기
        $form.append(text)
        // 게시글 수정 및 등록
        $form.submit();
    })

    // 기존 a태그 이벤트막기
    // 수정에서는 다운로드 안되고 목록만 보게 하기
    $basicFile.on("click", function (e) {
        e.preventDefault();
    });

//    파일 삭제 버튼
    $fileDeleteBtn.on("click", function () {
        const $thisFileWrap = $(this).closest('.modify-file-wrap')
        //화면 상숨기기
        $thisFileWrap.hide();
        // postDTO에 삭제할 파일을 담아주어 삭제시키기
        text += `<input type="hidden" name="fileIdsForDelete[${count}]" value="${$thisFileWrap.prop("id")}">`
        $form.append(text)
        ++count;
    });


    // 동적 화면 만 만든 파일 삭제 버튼
    $(document).on("click", '.active-file-delete-btn', function () {
        // 수정만 할 시 file change 이벤트가 일어나지 않으므로 file을 담을 변수 이벤트 함수안에 생성
        const files = $fileInput[0].files;
        // 기존 파일은  FileList 형식 유사배열 형태이기 때문에 배열로 변환시켜준다.
        let fileList = Array.from(files);
        //  삭제 버튼 누를때마다 plusText 초기화 시켜주기
        plusText = "";
        // 삭제버튼 여러개중 지금 이벤트 대상에 div 한 묶음 선언
        const $thisFileWrap = $(this).closest('.modify-file-wrap')
        // 지금 누른 삭제버튼에 index
        const index = $('.active-file-delete-btn').index($(this));

        //화면 상숨기기
        $thisFileWrap.remove();
        //삭제한 파일 값 삭제
        fileList.splice(index, 1);

        // 수정된 fileList 값을 담기위해 DataTransfer 생성자 변수에 담기
        const newFileList = new DataTransfer();
        // mdn 사이트 https://developer.mozilla.org/en-US/docs/Web/API/DataTransfer/items
        // item
        // 배열로 바꾼 파일 목록을 반복 돌려서 새로운 newFileList 라는 데이터전송 생성한 item에 차례로 담아주고
        fileList.forEach(file => newFileList.items.add(file));
        // 새로 담은 파일 정보를 기존 파일 정보로 바꾸어준다.
        $fileInput[0].files = newFileList.files;
        // 수정창에서 파일 등록 후 다시 수정 하고 싶어서 삭제 눌렀을때 flag 확인
        plusFileDeleteCheck= true;
    })

})