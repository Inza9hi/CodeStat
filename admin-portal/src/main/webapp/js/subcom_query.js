$(function() {
    initSubcoms();
    bindSubmitSubcomQueryForm();
});

function initSubcoms() {
    $.get("dict/subcoms?type="+"tt", function (data) {
        var $subcom_select = $("#subcomSelect");
        for (var i = 0;i < data.length;i++){
            var option_content="<option value='"+data[i].id+"'>"+data[i].id+"--"+data[i].name+"</option>";
            $subcom_select.append(option_content);
        }

    });
    $.get("dict/subcoms?type=BJ", function (data) {
        var $subcom_select = $("#subcomSelectBj");
        for (var i = 0;i < data.length;i++){
            var option_content="<option value='"+data[i].id+"'>"+data[i].id+"--"+data[i].name+"</option>";
            $subcom_select.append(option_content);
        }

    });

}

function bindSubmitSubcomQueryForm(event) {
    $("#subcomQueryForm").submit(function (event) {
        event.preventDefault();
        var sqlText = $("#sqlText").val();
        var deptIds = $("#subcomSelect").val();
        var deptIdsBj = $("#subcomSelectBj").val();
        var maxSize = Number($("#maxSize").val());
        var deptId = [null];

        //$.post("dept/sqlQuery", {
        //    sql: sqlText,
        //    deptIds: deptIds,
        //    maxSize:10
        //
        //}, function (data) {
        //    showSuccessDialog();
        //    console.log(data);
        //    for (var i = 0;i < data.length;i++) {
        //        var columnNames = data[i]
        //    }
        //
        //});

        $.ajax({
            type:'POST',
            contentType:"application/json",
            url: "dept/sqlQuery",
            processData:false,
            data: JSON.stringify({
                "sql": sqlText,
                "deptIds": deptId.concat(deptIds).concat(deptIdsBj),
                "maxSize": maxSize
            }),

            //{
            //        "sql": sqlText,
            //        "deptIds": deptIds,
            //        "maxSize":10
            //    },


            success: function(data){
                $("#queryResult").empty();
                renderQueryResults(data);
            }
        });

    })
}

function renderQueryResults(data){

    for (var i = 0;i < data.length;i++) {//循环分院，每一个分院都是一个Table
        var result= data[i];
        var deptId = result.deptId;
        var results = result.results;
        var columnNames = result.columnNames;



        var theaderContent ="";
        for (var j = 0;j < columnNames.length;j++){
            theaderContent += "<th>"+columnNames[j]+"</th>";
        }
        var theader = " <thead> "
            +"<tr>"
            +theaderContent
            +"</tr>"
            +"</thead>";


        var tableBody =" <tbody>";
        for (var j = 0;j < results.length;j++) {
            var resultRow = results[j];
            var resultRowContent = "<tr>";
            for (var k = 0; k < resultRow.length; k++) {
                resultRowContent= resultRowContent + "<td>"+resultRow[k] +"</td>";
            }
            resultRowContent+= "</tr>";
            tableBody +=resultRowContent;
        }
        tableBody += "</tbody>";

        var table = " <table id='result"+deptId+ "' class='table table-hover'> ";
        table = table+ theader+tableBody +"</table>";

        var tableHeader = "<div class='panel-heading'>分院："+deptId+"</div>" ;
        var tableDiv =    " <div class='panel panel-default table-responsive'>" + tableHeader + table + "</div>";
        console.log(tableDiv);
        $("#queryResult").append(tableDiv);
    }
}