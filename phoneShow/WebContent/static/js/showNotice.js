$(function(){
	//alert("ID:"+$("#ID").val());
	console.log($("#date").innerHTML);
	var json={"id":$("#ID").val()};
	var _param={
			url: "/phoneShow/page/getNotice.do",
			type: "GET",
			datatype: 'json',
			data:json,
			scriptCharset:'UTF-8',
			success: function(data){
				//{"type":type,"title":title
				//alert(data.date);
				$("#title").html(data.title);
				$("#date").html(data.date);
				$("#content").html(data.content);
			}
		};
		$.ajax(_param);
})