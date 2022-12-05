layui.use(['table', 'admin', 'HttpRequest', 'func'], function () {
  var $ = layui.$;
  var table = layui.table;
  var HttpRequest = layui.HttpRequest;
  var admin = layui.admin;
  var func = layui.func;

  /**
   * 数据库信息表管理
   */
  var Question = {
    tableId: "questionTable"
  };

  /**
   * 初始化表格的列
   */
  Question.initColumn = function () {
    return [[
      {type: 'checkbox'},
      {field: 'id', hide: true, title: '主键id'},
      {field: 'topic', align: "center", sort: true, title: '题目'},
      {align: 'center', toolbar: '#tableBar', title: '操作'}
    ]];
  };

  /**
   * 点击查询按钮
   */
  Question.search = function () {
    var queryData = {};
    queryData['topic'] = $("#topic").val();
    table.reload(Question.tableId, {
      where: queryData, page: {curr: 1}
    });
  };

  /**
   * 弹出添加对话框
   */
  Question.openAddDlg = function () {
    func.open({
      title: '添加题目',
      content: Feng.ctxPath + '/view/question/add',
      tableId: Question.tableId
    });
  };

  /**
   * 点击删除
   *
   * @param data 点击按钮时候的行数据
   */
  Question.onDeleteItem = function (data) {
    var operation = function () {
      var request = new HttpRequest(Feng.ctxPath + "/question/delete", 'post', function (data) {
        Feng.success("删除成功!");
        table.reload(Question.tableId);
      }, function (data) {
        Feng.error("删除失败!" + data.message + "!");
      });
      request.set("id", data.id);
      request.start(true);
    };
    Feng.confirm("是否删除?", operation);
  };

  // 渲染表格
  var tableResult = table.render({
    elem: '#' + Question.tableId,
    url: Feng.ctxPath + '/question/page',
    page: true,
    height: "full-158",
    cellMinWidth: 100,
    parseData: Feng.parseData,
    cols: Question.initColumn()
  });

  // 搜索按钮点击事件
  $('#btnSearch').click(function () {
    Question.search();
  });

  // 添加按钮点击事件
  $('#btnAdd').click(function () {
    Question.openAddDlg();
  });

  // 工具条点击事件
  table.on('tool(' + Question.tableId + ')', function (obj) {
    var data = obj.data;
    var layEvent = obj.event;

    if (layEvent === 'delete') {
      Question.onDeleteItem(data);
    }
  });
});
