<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>HOME PAGE</h2>

<a href='${contextPath}/app/test/login.pro'>LOGIN</a>
<a href='${contextPath}/app/test/wait?timeout=150'>150</a>
<a href='${contextPath}/app/test/wait?timeout=300'>300</a>
<a href='${contextPath}/app/test/wait?timeout=500'>500</a>
<a href='${contextPath}/app/test/wait?timeout=1000'>1000</a>
<a href='${contextPath}/app/test/wait?timeout=1500'>1500</a>
<a href='${contextPath}/app/test/wait?timeout=3000'>3000</a>
<a href='#ContentActivity.ContentPlace:/memo-portal/'>HOME ACTIVITY</a>
<a href='http://google.com/'>GOOGLE.COM</a>

<c:if test="${not empty contextUser}">
	<ul>
		<li>Nickname: ${contextUser.nickname}</li>
		<li>Fullname: ${contextUser.fullname}</li>
		<li>Gender:   ${contextUser.gender}</li>
		<li>Postcode: ${contextUser.postcode}</li>
		<li>Email:    ${contextUser.email}</li>
		<li>Country:  ${contextUser.country}</li>
		<li>Language: ${contextUser.language}</li>
		<li>Timezone: ${contextUser.timezone}</li>
	</ul>
</c:if>
