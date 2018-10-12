var layerIndex = -1;
layui.use(['table', 'layer', 'form', 'laydate'], function () {
  var table = layui.table, layer = layui.layer, form = layui.form, laydate = layui.laydate;

  var $ = layui.$, approveProcessTable = null;

  //初始化日期
  laydate.render({
    elem: '#startTime' //指定元素
  });
  laydate.render({
    elem: '#endTime' //指定元素
  });


  window.active = {
    reload: function (data) {
      //执行重载
      approveProcessTable.reload({
        page: {
          curr: 1 //重新从第 1 页开始
        }
        , where: data
      });
    },
    update: function () {
      //获取选中行
      var checkStatus = table.checkStatus('approveProcessTable'); //test即为基础参数id对应的值
      if (checkStatus.data.length != 1) {
        layer.msg('请选择一条数据', {time: 2000});
        return;
      }
      //修改
      layerIndex = layer.open({
        type: 2,
        title: '图书信息',
        area: ['700px', '380px'],
        maxmin: true,
        content: '/book/edit?id=' + checkStatus.data[0].id,
        end: function () {
          //刷新表格
          approveProcessTable.reload({
            page: {
              curr: 1 //重新从第 1 页开始
            }
          });
        }
      });
    },
    record: function () {
      //跳转页面
      location.href = "/book/record"
    },
    lend: function () {
      //获取选中行
      var checkStatus = table.checkStatus('approveProcessTable'); //test即为基础参数id对应的值
      if (checkStatus.data.length != 1) {
        layer.msg('请选择一条数据', {time: 2000});
        return;
      }
      //借书
      layerIndex = layer.open({
        type: 2,
        title: '借出图书',
        area: ['700px', '520px'],
        maxmin: true,
        content: '/book/record/lend?bookId=' + checkStatus.data[0].id,
        end: function () {
          //刷新表格
          approveProcessTable.reload({
            page: {
              curr: 1 //重新从第 1 页开始
            }
          });
        }
      });
    },
    delete: function () {
      //获取选中行
      var checkStatus = table.checkStatus('approveProcessTable'); //test即为基础参数id对应的值
      if (checkStatus.data.length <= 0) {
        layer.msg('请选择至少1条数据', {time: 2000});
        return;
      }

      //询问框

      layer.confirm('确定要删除选定的 ' + checkStatus.data.length + ' 本图书吗？', {
        btn: ['确定', '取消'] //按钮
      }, function () {
        var idArray = [];

        for (var i = 0; i < checkStatus.data.length; i++) {
          idArray.push(checkStatus.data[i].id)
        }
        var ids = idArray.join(",");
        //删除
        $.ajax({
          url: '/book/del',
          type: 'POST',
          dataType: "json",
          data: ids,
          contentType: 'application/json;charset=utf-8',
          success: function (result) {
            if (result.code == 'SUCCESS') {
              var idx = layer.alert('删除成功', {icon: 1}, function () {
                //刷新表格
                approveProcessTable.reload({
                  page: {
                    curr: 1 //重新从第 1 页开始
                  }
                });
                layer.close(idx);
              });

            } else {
              layer.msg(result.message, {icon: 2});
            }
          }
        })
      }, function () {

      });
    },
    add: function () {
      //新增
      layerIndex = layer.open({
        type: 2,
        title: '图书信息',
        area: ['700px', '380px'],
        maxmin: true,
        content: '/book/edit',
        end: function () {
          //刷新表格
          approveProcessTable.reload({
            page: {
              curr: 1 //重新从第 1 页开始
            }
          });
        }
      });
    }
  };

  //第一个实例
  approveProcessTable = table.render({
    elem: '#approveProcessTable',
    height: 600,
    cellMinWidth: 100
    , url: '/book/list' //数据接口
    , page: true //开启分页
    , cols: [[ //表头
      {type: 'checkbox', fixed: 'left'},
      {field: 'name', title: '名称'},
      {field: 'author', title: '作者'},
      {
        field: 'type', title: '类型', templet: function (d) {
          if (d.type == 0) {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">java</span>';
          } else if (d.type == 1) {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">python</span>';
          } else if (d.type == 2) {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">数据库</span>';
          } else {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">其他</span>';
          }
        }
      },
      {field: 'press', title: '出版社'},
      {field: 'total', title: '库存总数'},
      {field: 'current', title: '当前剩余'},
      {field: 'amount', title: '已借出数'},
      {field: 'price', title: '单价(￥/m)'}
    ]],
    limit: 10,
    request: {
      pageName: 'pageIndex' //页码的参数名称，默认：page
      , limitName: 'pageSize' //每页数据量的参数名，默认：limit
    },
    response: {
      statusName: 'code' //数据状态的字段名称，默认：code
      , statusCode: 'SUCCESS' //成功的状态码，默认：0
      , countName: 'rows' //数据总数的字段名称，默认：count
      , dataName: 'result' //数据列表的字段名称，默认：data
    }
  });

  $('button[data-type="update"]').on('click', window.active.update);
  $('button[data-type="delete"]').on('click', window.active.delete);
  $('button[data-type="add"]').on('click', window.active.add);
  $('button[data-type="lend"]').on('click', window.active.lend);
  $('button[data-type="record"]').on('click', window.active.record);

  //监听提交
  form.on('submit(searchForm)', function (data) {
    window.active.reload(data.field);
    return false;
  });
});

Date.prototype.Format = function (fmt) { //author: meizz
  var o = {
    "M+": this.getMonth() + 1,                 //月份
    "d+": this.getDate(),                    //日
    "h+": this.getHours(),                   //小时
    "m+": this.getMinutes(),                 //分
    "s+": this.getSeconds(),                 //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds()             //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}