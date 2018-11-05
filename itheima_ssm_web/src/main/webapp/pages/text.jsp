<%--
  Created by IntelliJ IDEA.
  User: 20149
  Date: 2018/11/4
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>

    <script language="javascript">

        /*@param fromObjSelectId 移动item的原select组件id
        @param toObjectSelectId 移动item将要进入的目标select组件id*/
        function move1() {
            var objSelect = document.getElementById("fromObjSelectId");
            var delNum = 0;
            if (null != objSelect && typeof(objSelect) != "undefined") {
                for(var i=0;i<objSelect.options.length;i=i+1) {
                    if(objSelect.options[i].selected) {
                        addOneItemToSelect("toObjectSelectId",objSelect.options[i].text,objSelect.options[i].value)
                        objSelect.options.remove(i);
                        i = i - 1;
                    }
                }
            }
        }

       function move2() {
           var objSelect = document.getElementById("toObjectSelectId");
           var delNum = 0;
           if (null != objSelect && typeof(objSelect) != "undefined") {
               for(var i=0;i<objSelect.options.length;i=i+1) {
                   if(objSelect.options[i].selected) {
                       addOneItemToSelect("fromObjSelectId",objSelect.options[i].text,objSelect.options[i].value)
                       objSelect.options.remove(i);
                       i = i - 1;
                   }
               }
           }
       }

        /*@param objSelectId 将要加入item的目标select组件的id
@param objItemText 将要加入的item显示的内容
@param objItemValue 将要加入的item的值*/
        function addOneItemToSelect(objSelectId,objItemText,objItemValue) {
            var objSelect = document.getElementById(objSelectId);
            if (null != objSelect && typeof(objSelect) != "undefined") {
                //判断是否该值的item已经在select中存在
                if(isSelectItemExit(objSelectId,objItemValue)) {
                    $.alert('提示消息','该值的选项已经存在!','info');
                } else {
                    var varItem = new Option(objItemText,objItemValue);
                    objSelect.options.add(varItem);
                }
            }
        }
/*@param objSelectId 将要验证的目标select组件的id
@param objItemValue 将要验证是否存在的值*/
        function isSelectItemExit(objSelectId,objItemValue) {
            var objSelect = document.getElementById(objSelectId);
            var isExit = false;
            if (null != objSelect && typeof(objSelect) != "undefined") {
                for(var i=0;i<objSelect.options.length;i++) {
                    if(objSelect.options[i].value == objItemValue) {
                        isExit = true;
                        break;
                    }
                }
            }
            return isExit;
        }
    </script>
</head>
<body>

<div>
    <select id="fromObjSelectId" name=s1 multiple>
        <option>A</option>
        <option>B</option>
        <option>C</option>
        <option>D</option>
    </select>
    <select id="toObjectSelectId" name=s2 multiple>

    </select>
</div>
<p>
    <button class="fa fa-fw fa-arrow-left"  onclick="move1();">添加角色</button>
    <button  onclick="move2();">撤销角色</button>
</p>


</body>
</html>