<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	$(document).ready(function(){
		$('button[type=submit]').click(function(){
			if($('input[name=word]').val() == ''){
				alert('검색할 내용을 입력해주세요');
				document.frm.word.focus();
			}
		});
	});
</script>

<div>
	<FORM name='frm' action="<%=request.getContextPath()%>/carpool/search_carpool.do">
	    <ASIDE style='float: right;'>
	      <SELECT name='category'> <!-- 검색 컬럼 -->
	        
	        <OPTION value='rname'>작성자</OPTION>
	        <OPTION value='rstart'>출발지</OPTION>
	        <OPTION value='rend'>도착지</OPTION>
	        <!-- <OPTION value='content'>내용</OPTION>
	        <OPTION value='title_content'>제목+내용</OPTION> -->
	      </SELECT>
	      <input type='text' name='word' placeholder="특수문자는 사용할수 없습니다.">
	      <button type='submit'>검색</button>    
	    </ASIDE> 
	</FORM>
  <div class='menu_line' style='clear: both;'></div>
</div>