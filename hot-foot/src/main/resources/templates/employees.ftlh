<#import "/spring.ftl" as spring>
<html>
<h1>Employees List</h1>
<ul>
<#list employees as emp>
    <li>${emp}</li>
</#list>
</ul>
<br>
<a href="/logout">Logout</a>
</html>