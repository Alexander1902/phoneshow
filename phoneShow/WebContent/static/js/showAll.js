var totalSize=10;
var totalPage=1;
var curpage=1;
var pageSize=10;
$(function(){	
	initData();
	$("#seach").click(function(){
		search();
	});
	$("#clearbtn").click(function(){
		relaod();
	});
})
//刷新整个页面
function relaod(){
	$("#type").val("");
	$("#title").val("");
	$("#start").val("");
	$("#end").val("");
	$("#pagesize").val("10");
	pageSize=10;
	initData();
}
//翻页
function changPage(pageno,allpage,size,jsondata){
	//分页
	$("#page").paging({
		pageNo:pageno,//当前页码
		totalPage: allpage,//总页数
		totalSize: size,//数据总量
		callback: function(num) {
			curpage=num;
			console.log("当前页："+curpage+",总页数："+totalPage+",每页数量："+pageSize);
			//var json={"pageno":num,"pagesize":pageSize};
			jsondata.pageno=num;
			jsondata.pagesize=pageSize;
			fulshHtml(jsondata);
		}
	})
}
//初始页面
function initData(json){//页码，每页的size
	if(json!=null){
		console.log(json);
	}else{
		json={"pageno":1,"pagesize":10};
	}
	var _param={
			url: "/phoneShow/page/getoffice.do",
			type: "GET",
			datatype: 'json',
			data:json,
			scriptCharset:'UTF-8',
			success: function(data){
				totalPage=data.pagecount;//总页数
				totalSize=data.datacount;//数据总量
				curpage=data.pageno;
				showHtml(data.office);
				changPage(curpage,totalPage,totalSize,json);
			}
		};
		
		$.ajax(_param);
}
//查询
function search(num){
	var type=$("#type").val();
	var title=$("#title").val();
	var titleencode=encodeURI(encodeURI(title));
	var start=$("#start").val();
	var end=$("#end").val();
	/*if(((start==""||start==null)&&(end!=""||end!=null))||((start!=""||start!=null)&&(end==""||end==null))){
		alert("请选择完整日期");
		return;
	}*/
	if(start==""&&end!=""){
		alert("请选择开始日期");
		return;
	}
	if(start==null&&end!=null){
		alert("请选择开始日期");
		return;
	}
	if(start!=""&&end==""){
		alert("请选择结束日期");
		return;
	}
	if(start!=null&&end==null){
		alert("请选择结束日期");
		return;
	}

	alert(titleencode);
	console.log("type:"+type+",title:"+title+",start:"+start+",end:"+end);
	var cond={"pageno":1,"pagesize":pageSize,"type":type,"title":titleencode,"start":start,"end":end};
	initData(cond);
}
/*
 * 编号,标题 ,类型 ,浏览,时间,操作
 */
function showHtml(data){
	$("#officeTable").html("");
	for(var o=0;o<data.length;o++){
		var html="";
		html+="<tr>";
		html+="<td>"+(o+1);
		html+="</td>";
		html+="<td id='"+data[o].id+"'>"+data[o].title;
		html+="</td>";
		html+="<td>"+data[o].type;
		html+="</td>";
		if(data[o].type==3){//3是公告
		html+="<td><a href='/phoneShow/showNotice.jsp?id="+data[o].id+"' target='_blank'>查看</a>";
		}else{
		html+="<td><a href='/phoneShow/office/"+data[o].url+"' target='_blank'>查看</a>";
		}
		html+="</td>";
		html+="<td>"+data[o].date;
		html+="</td>";
		html+="</tr>";
		$("#officeTable").append(html);
	}
}
//根据翻页刷新页面
function fulshHtml(jsondata){
	var _param={
			url: "/phoneShow/page/getoffice.do",
			type: "POST",
			datatype: 'json',
			data:jsondata,
			success: function(data){
				totalPage=data.pagecount;//总页数
				totalSize=data.datacount;//数据总量
				showHtml(data.office);
			}
		};
		$.ajax(_param);
}
//改变每页显示数量
function pageSizeChonge(){
	var size=$("#pagesize").val();
	pageSize=size;
	var json={"pageno":1,"pagesize":pageSize};
	//initData(json);
	search();
}