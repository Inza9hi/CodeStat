<#import "tags/dashboard.ftl" as dashboard>


<h1>分院查询</h1>


<div  id="subcomInputForm">
    <form id="subcomQueryForm" class="form-horizontal">
        <div class="form-group">
            <label for="sqlText" class="col-sm-2 control-label">输入Sql</label>
            <div class="col-sm-9">
                <input type="text" id="sqlText" name="sqlText" class="form-control"  required/>
            </div>
        </div>
        <div class="form-group">
            <label for="subcomSelectBj" class="col-sm-2 control-label">选择分院(北京)</label>
            <div class="col-sm-9">
                <select name="subcomSelectBj" multiple id="subcomSelectBj" class="form-control">
                </select>
            </div>
         </div>
         <div class="form-group">
            <label for="subcomSelect" class="col-sm-2 control-label">选择分院(外地)</label>
            <div class="col-sm-9">
                <select name="subcomSelect" multiple id="subcomSelect" class="form-control">
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="maxSize" class="col-sm-2 control-label">最大返回行数</label>
            <div class="col-sm-1">
                <input type="number" id="maxSize" name="maxSize" class="form-control" data-toggle="tooltip" data-placement="bottom" title="最大返回行数" value="10" required />
            </div>
        </div>

        <button type="reset" class="btn btn-inverse">重置</button>
        <button type="submit" class="btn btn-primary">查询</button>
    </form>
</div>

<br/>
<br/>

<div  id="queryResult" >
    <#--<div class="panel panel-default">-->
        <#--<div class="panel-heading">Header</div>-->
        <#--<table id="result" class="table table-hover">-->
            <#--<thead>-->
            <#--<tr>-->
                <#--<th>撒旦</th>-->

            <#--</tr>-->
            <#--</thead>-->
            <#--<tbody>-->
            <#--</tbody>-->
        <#--</table>-->

    <#--</div>-->


</div>


<h1>帮助</h1>

<h2>常用Sql</h2>
<ol>
    <li>查询人员信息： <pre>SELECT SC.SGLCHECKID,SC.NAME,SC.IDCARD,SC.IDCARDTYPE,SC.SEX,SC.MOBILEPHONE,SC.AGE, SC.REGISTDATE,SC.CHECKSTATUS,SC.HOMEPHONE,SC.OFFICEPHONE,SC.ENTERPRICE
        FROM T_SINGLE_CHECKINFO  sc WHERE  SC.CHECKSTATUS >='1120' AND SC.CHECKSTATUS !='1999'</pre></li>
    <li>常用表：<pre>
        select * from T_SINGLE_CHECKINFO -- 人员信息
        select * from T_FORM  --问卷
        select * from T_FORM_QUESTION --问卷
        select * from T_FORMQUESTION --问卷
        select * from T_FORMOPTION  -- 问卷
        select * from T_FORMANSWER  -- 问卷答案
        select  * FROM T_FORMQUESTIONANSWER  --问卷答案
        select  * FROM T_SINGLE_ANALYZE -- 汇总分析
        select  * FROM T_SINGLE_DIAGNOSE  --诊断结果
        select  * FROM T_SINGLE_RESULT   --检查结果
        select  * FROM T_SINGLE_CHECKMODULE   --个人检查项目
    </pre>
    </li>
    <li>
        以下是基础表:<pre>
    select  * FROM T_EMP   --- 人员信息
    select  * FROM T_CHECKITEM    ---检查项目

    select  * FROM T_CHECKMODULE_CHECKITEM
    select  * FROM T_CHECKMODULE         ---NEEDED
    select  * FROM T_CHECKUNIT   --检查单元
    select  * FROM T_ABANDONREASON  ---放弃原因 ---NEEDED
    select  * FROM V_SEX     --性别
    select  * FROM T_DEPT    --部门
    select  * FROM T_NATION  --国籍
    select  * FROM T_NATIONALITY -- 民族
    select  * FROM V_MARRY -- 婚否 </pre>
    </li>
</ol>
<h2>其他说明</h2>
<ol>
    <li>默认不选分院从数据中心取数</li>
</ol>
<@dashboard.successDialog "success-dialog" />
<script src="lib/jquery/jquery-2.1.4.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script src="js/subcom_query.js"></script>
<script src="js/common.js"></script>
