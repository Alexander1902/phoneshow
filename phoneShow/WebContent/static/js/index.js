
$(function(){
	var pageUrl = (location.pathname + location.search).toLowerCase();
	var meaulink=[];
	var len=$("#side-menu").children("li").length;
	for(var i=0;i<len;i++){
		var id=$("#side-menu").children("li").eq(i).find("a").attr("href").toLowerCase();
		if(pageUrl==id){
		
			$("#side-menu").children("li").eq(i).addClass("active");
			$("#side-menu").children("li").eq(i).siblings().removeClass("active");
		}
	}
	$("#side-menu").children("li").click(function() {
		if (!$(this).hasClass("active")) {
			 $(this).addClass("active");
		}
	});
})