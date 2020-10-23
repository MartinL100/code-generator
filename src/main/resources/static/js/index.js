function test() {
    //js获取数据
    var module = document.getElementById("module").value;
    var imgFile = document.getElementById("file").files;   //文件是通过.files取数据
    var classHasPrefix= document.getElementsByName("classHasPrefix");
    var author = document.getElementById("author").value;
    for (var i = 0 ; i<classHasPrefix.length;i++){
        if (classHasPrefix[i].checked){
            classHasPrefix = classHasPrefix[i].value
        }
    }



    var formData = new FormData();  //通过FormData拼装数据
    formData.append("module", module);
    formData.append("author", author);
    formData.append("classHasPrefix", classHasPrefix);
    formData.append("file", imgFile[0]);  //因为取出的文件是数组，所以取第一项

//通过ajax发送数据
    $.ajax({
        url: "/code",
        type: "post",
        contentType: false,
        data: formData,
        processData:false,
        success: function(result) {
            // 创建get请求路径
            let downloadUrl = "/downLoadCode?fileName="+result;
            // 创建a标签
            let label = $("<a>");
            // 添加属性
            label.prop("href",downloadUrl);
            // 追加标签
            $("body").append(label);
            // 点击a标签，为什么要加[0]不懂，不加点击函数不生效
            label[0].click();
            // 点击后移除标签
            label.remove();
        }
    });
}


$("#generator-code").click(function () {
    test();
})

$("#downLoad-template").click(function () {
    // 创建get请求路径
    let downloadUrl = "/template";
    // 创建a标签
    let label = $("<a>");
    // 添加属性
    label.prop("href",downloadUrl);
    // 追加标签
    $("body").append(label);
    // 点击a标签，为什么要加[0]不懂，不加点击函数不生效
    label[0].click();
    // 点击后移除标签
    label.remove();
})