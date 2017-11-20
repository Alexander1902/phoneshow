$(function(){
	var ue = UE.getEditor('editor',{  
		toolbars: [[
		            'fullscreen', 'source','cleardoc', '|', 'undo', 'redo', '|',
		            'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', '|',
		            'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
		            'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
		            'directionalityltr', 'directionalityrtl', 'indent', '|',
		            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
		            'link', 'unlink', 'anchor', '|', 
		            'horizontal', 'date', 'time', 'spechars', '|',
		            'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
		            'print', 'preview', 'searchreplace', 'drafts', 'help'
		        ]]
	       });
	$("#submit").click(function(){
		var content=UE.getEditor('editor').getContent();
		var title = $("#title").val();
		if(content.length>=21650){
			alert("内容有些长，请减少格式或减少字数");
			return;
		}
		console.log(content.length);
		
		if(content.length==0||title.length==0){
			alert("标题或内容不能为空！");
			return;
		}
		submit(content,title);
	});
});
function submit(content,title){
	var json={"content":content,"title":title};
	var _param={
			url: "/phoneShow/page/notice.do",
			type: "POST",
			datatype: 'json',
			data:json,
			scriptCharset:'UTF-8',
			success: function(data){
				alert(data.stute);
				if(data.stute==1){
					alert("发布成功！");
					location.href="/phoneShow/page/editor.do";
				}else{
					alert("发布失败！");
				}
			}
		};
	$.ajax(_param);
}
/*function getContent() {
    var arr = [];
    arr.push("使用editor.getContent()方法可以获得编辑器的内容");
    arr.push("内容为：");
    arr.push(UE.getEditor('editor').getContent());
    alert(arr.join("\n"));
}*/