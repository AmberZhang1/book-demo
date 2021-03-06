layui.use(['table', 'layer', 'form', 'laydate'], function () {
  var layer = layui.layer;
  var laydate = layui.laydate;
  laydate.render({
    elem: '.date' //指定元素
  });

  var active = {
    submit: function () {
      var bookRecord = {
        id: null,
        bookId: null,
        userName: null,
        userPhone: null
      };
      bookRecord.bookId = $("#bookId").val();
      bookRecord.userName = $("#userName").val();
      bookRecord.userPhone = $("#userPhone").val();
      $.ajax({
        url: '/book/record/doLend',
        type: 'POST',
        dataType: "json",
        data: JSON.stringify(bookRecord),
        contentType: 'application/json;charset=utf-8',
        success: function (result) {
          if (result.code == 'SUCCESS') {
            layer.alert('借出成功', {icon: 1}, function () {
              parent.layer.close(window.parent.layerIndex);
            });
          } else {
            layer.msg(result.message, {icon: 2});
          }
        }
      })
    }
  };

  $("#save").on("click", active.submit);
});

/**
 * 序列化表单提交参数为对象
 * @author lily
 */
jQuery.fn.extend({
  serializeObject: function () {
    var a = this.serializeArray();
    var s = {},
        add = function (key, value) {
          value = jQuery.isFunction(value) ? value() : value;
          s[encodeURIComponent(key)] = value;
        };
    if (jQuery.isArray(a) || (a.jquery && !jQuery.isPlainObject(a))) {
      jQuery.each(a, function () {
        add(this.name, this.value);
      });

    }
    return s;
  }
});