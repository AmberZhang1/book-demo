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
    book: function () {
      //跳转页面
      location.href = "/book"
    },
    revert: function () {
      //获取选中行
      var checkStatus = table.checkStatus('approveProcessTable'); //test即为基础参数id对应的值
      if (checkStatus.data.length != 1) {
        layer.msg('请选择一条数据', {time: 2000});
        return;
      }

      if (checkStatus.data[0].state != 0) {
        layer.msg('此书已经归还！', {time: 2000});
        return;
      }

      layer.confirm('请确定用户已将书归还？', {
        btn: ['确定', '取消'] //按钮
      }, function () {

        var bookRecord = {
          id: null,
          bookId: null
        };
        bookRecord.id = checkStatus.data[0].id;
        bookRecord.bookId = checkStatus.data[0].bookId;
        $.ajax({
          url: '/book/record/revert',
          type: 'POST',
          dataType: "json",
          data: JSON.stringify(bookRecord),
          contentType: 'application/json;charset=utf-8',
          success: function (result) {
            if (result.code == 'SUCCESS') {
              var idx = layer.alert('还书成功 总价格:' + result.data, {icon: 1}, function () {
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
        });
      }, function () {

      });

    }
  };

  //第一个实例
  approveProcessTable = table.render({
    elem: '#approveProcessTable',
    height: 600,
    cellMinWidth: 100
    , url: '/book/record/list' //数据接口
    , page: true //开启分页
    , cols: [[ //表头
      {type: 'checkbox', fixed: 'left'},
      {field: 'name', title: '名称',templet:function (d) {
          return d.book.name
        }},
      {field: 'author', title: '作者',templet:function (d) {
          return d.book.author
        }},
      {
        field: 'type', title: '类型', templet: function (d) {
          if (d.book.type == 0) {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">java</span>';
          } else if (d.book.type == 1) {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">python</span>';
          } else if (d.book.type == 2) {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">数据库</span>';
          } else {
            return '<span class="layui-btn layui-btn-primary layui-btn-xs">其他</span>';
          }
        }
      },
      {field: 'userName', title: '租借人'},
      {field: 'userPhone', title: '租借人联系方式'},
      {field: 'press', title: '出版社',templet:function (d) {
          return d.book.press
        }},
      {field: 'startDate', title: '租借开始时间',templet: function (d) {
          return new Date(d.startDate).Format('yyyy-MM-dd hh:mm:ss');
        }},
      {field: 'endDate', title: '租借结束时间',templet: function (d) {
          if (d.endDate) {
            return new Date(d.endDate).Format('yyyy-MM-dd hh:mm:ss');
          }else {
            return "";
          }
        }},
      {field: 'state', title: '状态',templet:function (d) {
          if (d.state == 0) {
            return '<span class="layui-btn layui-btn-danger layui-btn-xs">未还</span>';
          } else if (d.state == 1) {
            return '<span class="layui-btn layui-btn-xs">已还</span>';
          }
        }},
      {field: 'totalPrice', title: '租书总价(￥)'}
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

  $('button[data-type="revert"]').on('click', window.active.revert);
  $('button[data-type="book"]').on('click', window.active.book);

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