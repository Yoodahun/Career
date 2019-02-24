moment.lang('ja', {
  weekdays: ["日曜日", "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日"]
});

var $table = $('table').DataTable();

function list(values, type) {
  for (let key in values) {
    const d = values[key];
    const name = [d.name];
    const id = [d.id];
    if (type === 'emp') {
      $('#Subordinate').append($('<option>').text(name).val(id));
    } else if (type === 'work') {
      $('#workType').append($('<option>').text(name).val(id));
    }
  }
}

function editRow($table, values, method) {
  $table.clear();
  let rh = 0; //休憩時間
  let rm = 0; //休憩時間分
  let h = 0; //勤務時間・時
  let m = 0; //勤務時間・分
  for (let key in values) {
    const d = values[key];
    console.log(d);
    const arr = [d.id, d.empId, d.startTime, d.endTime, d.workTypeName, d.restTime];
    let tr = null;
    if (method === 'post') {
      tr = $table.row.add(arr).draw().node();
    } else if (method === 'put') {
      console.log($table.row('.bg-info').data());
      tr = $table.row('.bg-info').data(arr).node();

    }
    $(tr).attr('data-workId', d.workId);
    $(tr).attr('data-date', d.date)

    if (d.restTime !== null) {
      const r = parseInt(d.restTime, 10);
      rh = Math.floor(rh) + Math.floor((r / 60));
      rm = rm + (r % 60);
      if (rm > 59) {
        rh += 1;
        rm = rm % 60;
      }
    }
    if (d.workTime !== null) {
      h = Math.floor(h) + Math.floor((d.workTime / 60));
      m = m + (d.workTime % 60);
      if (m > 59) {
        h += 1;
        m = m % 60;
      }
    }

    $('#totalWorkTime').text(Math.floor(h) + ':' + m);
    $('#restTime').text(Math.floor(rh) + ':' + rm);

  }
}

/* ---------------------------- */

$(function() {
  $.extend($.fn.dataTable.defaults, {
    language: {
      url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
    }
  });
  const $table = $('table').DataTable();


  $('#monthButton[type="radio"]').change(function() {
    $('#month').prop('disabled', false);
    $('#week').prop('disabled', true);
    $('#option1').prop('disabled', true);
    $('#option2').prop('disabled', true);
  });
  $('#weekButton[type="radio"]').change(function() {
    $('#month').prop('disabled', true);
    $('#week').prop('disabled', false);
    $('#option1').prop('disabled', true);
    $('#option2').prop('disabled', true);
  });
  $('#optionButton[type="radio"]').change(function() {
    $('#month').prop('disabled', true);
    $('#week').prop('disabled', true);
    $('#option1').prop('disabled', false);
    $('#option2').prop('disabled', false);
  });

  $('#return').click(function() {
    location.href = 'initial.html';
  });

  $('button.insert').click(function() {
    $('#dialogLabel').text('入力');
    $('#method').val('post');
    if ($('#loginuser').attr('data-positionId') === 4) {
      $('#date').attr('min', moment().format('YYYY-MM-DD'));
    }

    $('.form-data')[0].reset();
    $('#workType').val(1);
    $('#dialog').modal('show');
  });

  $('.search').click(function() {
    $table.clear();
    const params = $('.form-search').serializeArray();
    console.log(params);

    const startOfMonth = moment(params[2].value).startOf('month').format('YYYY-MM-DD');
    const endOfMonth = moment(params[2].value).endOf('month').format('YYYY-MM-DD');
    const startOfWeek = moment(params[2].value).startOf('week').format('YYYY-MM-DD');
    const endOfWeek = moment(params[2].value).endOf('week').format('YYYY-MM-DD');
    const startOfDate = moment(params[2].value).format('YYYY-MM-DD');

    if (params[1].value === 'month') {
      params[2] = {
        'name': 'startDate',
        'value': startOfMonth
      };
      params[3] = {
        'name': 'endDate',
        'value': endOfMonth
      };
    } else if (params[1].value === 'week') {
      params[2] = {
        'name': 'startDate',
        'value': startOfWeek
      };
      params[3] = {
        'name': 'endDate',
        'value': endOfWeek
      };
    } else if (params[1].value === 'option') {
      // const endOfDate = moment(params[2].value).format('YYYY-MM-DD');
      params[2] = {
        'name': 'startDate',
        'value': startOfDate
      };
      // params[3] = {
      //   'name': 'endDate',
      //   'value': endOfDate
      // };
    }
    console.log(params[1].value);
    if (params[1].value === "Invalid date") {
      alert('月、週、任意にのいずれかに値を入れてください。');
      return false;
    }
    console.log(params);
    $.getJSON(
      'report/record', params
    ).done(function(r) {
      console.log(r);
      if (!r || r.statusCode === 500) {
        alert(r.reason);
        return false;
      } else {
        if (r.data.length === 0) {
          $table.clear();
          alert('この期間にはデータはありません。');
        } else {
          $table.clear();
          editRow($table, r.data, 'post');
        }
      }
    });
  });

  $('table > tbody').on('click', 'tr', function() {
    const today = moment().format('YYYY-MM-DD');

    $('#dialogLabel').text('更新');
    $('#method').val('put');
    $('table > tbody > tr').removeClass('bg-info');
    const $tr = $(this);
    $tr.addClass('bg-info');
    const d = $table.row('.bg-info').data();
    console.log(d);

    const dataDate = moment(d[0]).format('YYYY-MM-DD');
    if ($('#loginuser').attr('data-positionId') === '4') {
      if (dataDate < today) { ///???
        return false;
      }
    }

    $('#date').val(d[0]);
    $('#id').val(d[1]);
    $('#startTime').val(d[2]);
    $('#endTime').val(d[3]);
    $('#workType').val($tr.attr('data-workId')); //確認すること
    /* 勤務種類 selectのvalueによって選択できないように*/
    if ($tr.attr('data-workId') === '5' || $tr.attr('data-workId') === '6' ||
      $tr.attr('data-workId') === '7' || $tr.attr('data-workId') === '8' ||
      $tr.attr('data-workId') === '9' || $tr.attr('data-workId') === '10') {
      $('#startTime').prop('disabled', true);
      $('#endTime').prop('disabled', true);
    } else {
      $('#startTime').prop('disabled', false);
      $('#endTime').prop('disabled', false);
      // $('#startTime').val($('#start').text());
      // $('#endTime').val($('#end').text());
    }
    $('#dialog').modal('show');
  });

  $('button.ok').click(function() {

    if ($('#date').val() === '') {
      alert('日付を入力して下さい。');
      return false;
    }
    if (($('#workType').val() === '1' || $('#workType').val() === '2' ||
        $('#workType').val() === '3' || $('#workType').val() === '4') &&
      $('#startTime').val() === '') {
      alert('出社時間を入力して下さい。');
      return false;
    }
    const params = $('.form-data').serializeArray();
    console.log(params);

    if (!($('#workType').val() === '5' || $('#workType').val() === '6' ||
        $('#workType').val() === '7' || $('#workType').val() === '8' ||
        $('#workType').val() === '9' || $('#workType').val() === '10')) {

      if ($('#method').val() === 'put') {
        if ( (params[5].value !== '') && (params[4].value > params[5].value)  ) {
          console.log(params[5].value);
          alert('退社時間を出社時間より早く設定することはできません。');
          return false;
        }
      } else {

        if ( (params[5].value !== '') && (params[4].value > params[5].value) ) {
          console.log(params[5].value);
          alert('退社時間を出社時間より早く設定することはできません。');
          return false;
        }
      }
    }
    const startTimeHour = $('#startTime').val().split(':')[0];
    const startTimeMinutes = $('#startTime').val().split(':')[1];

    const endTimeHour = $('#endTime').val().split(':')[0];
    const endTimeMinutes = $('#endTime').val().split(':')[1];

    if(startTimeHour >= 9 && startTimeMinutes >= 1 ) {
      $('#workType').val(2);
    } else if (endTimeHour <= 17 && endTimeMinutes <= 59 ) {
      $('#workType').val(3);
    } else if ( (startTimeHour >= 9 && startTimeMinutes >= 1 ) &&
                (endTimeHour <= 17 && endTimeMinutes <= 59 )  ) {
      $('#workType').val(11);
      }
      params = $('.form-data').serializeArray();
      console.log(params);

    $.post(
      'report/record', params
    ).done(function(r) {
      console.log(r);
      if (!r || r.statusCode === 500) {
        alert(r.reason);
        return false;
      }
    });

    if ($('#method').val() === 'put') {

      const params1 = $('.form-search').serializeArray();
      console.log(params1);
      const startOfMonth = moment(params1[2].value).startOf('month').format('YYYY-MM-DD');
      const endOfMonth = moment(params1[2].value).endOf('month').format('YYYY-MM-DD');
      const startOfWeek = moment(params1[2].value).startOf('week').format('YYYY-MM-DD');
      const endOfWeek = moment(params1[2].value).endOf('week').format('YYYY-MM-DD');
      const startOfDate = moment(params1[2].value).format('YYYY-MM-DD');

      if (params1[1].value === 'month') {
        params1[2] = {
          'name': 'startDate',
          'value': startOfMonth
        };
        params1[3] = {
          'name': 'endDate',
          'value': endOfMonth
        };
      } else if (params1[1].value === 'week') {
        params1[2] = {
          'name': 'startDate',
          'value': startOfWeek
        };
        params1[3] = {
          'name': 'endDate',
          'value': endOfWeek
        };
      } else if (params1[1].value === 'option') {
        // const endOfDate = moment(params1[2].value).format('YYYY-MM-DD');
        params1[2] = {
          'name': 'startDate',
          'value': startOfDate
        };
        // params1[3] = {
        //   'name': 'endDate',
        //   'value': endOfDate
        // };
      }
      $table.clear();
      $.getJSON(
        'report/record', params1
      ).done(function(r) {

        if (!r || r.statusCode === 500) {
          alert(r.reason);
          return false;
        } else {
          if (r.data.length === 0) {
            // $table.clear();
            alert('この期間にはデータはありません。');
          } else {
            // $table.clear();
            editRow($table, r.data, 'post');

          }
        }
      });
    }
    $('#dialog').modal('hide');
  });


  $('#workType').on('click', function() {

    /* 勤務種類 selectのvalueによって選択できないように*/
    if ($(this).val() === '5' || $(this).val() === '6' || $(this).val() === '7' || $(this).val() === '8' ||
      $(this).val() === '9' || $(this).val() === '10') {
      console.log('!!');
      $('#startTime').prop('disabled', true);
      $('#endTime').prop('disabled', true);
    } else {
      $('#startTime').prop('disabled', false);

      $('#endTime').prop('disabled', false);
    }
  });

  $('#endTime').click(function() {
    console.log($('#startTime').val());
    if ($('#endTime').val() === '') {
      $('#endTime').val($('#startTime').val());
    }
    $('#endTime').attr('min', $('#startTime').val());
  });

  $('#date').change(function() {
    console.log($('#date').val());
    const m = moment($('#date').val());
    const f = m.format('dddd');
    console.log(f);
    if (f === "土曜日" || f === "日曜日") {
      $('#workType').val(4);
      $("#workType > option:nth-child(1)").prop("disabled", true);
      $("#workType > option:nth-child(2)").prop("disabled", true);
      $("#workType > option:nth-child(3)").prop("disabled", true);
      $("#workType > option:nth-child(4)").prop("disabled", false);
      $("#workType > option:nth-child(5)").prop("disabled", false);
      $("#workType > option:nth-child(6)").prop("disabled", true);
      $("#workType > option:nth-child(7)").prop("disabled", true);
      $("#workType > option:nth-child(8)").prop("disabled", true);
      $("#workType > option:nth-child(9)").prop("disabled", false);
      $("#workType > option:nth-child(10)").prop("disabled", true);
      $("#workType > option:nth-child(11)").prop("disabled", true);
    } else {
      $("#workType > option:nth-child(1)").prop("disabled", false);
      $("#workType > option:nth-child(2)").prop("disabled", false);
      $("#workType > option:nth-child(3)").prop("disabled", false);
      $("#workType > option:nth-child(4)").prop("disabled", true);
      $("#workType > option:nth-child(5)").prop("disabled", true);
      $("#workType > option:nth-child(6)").prop("disabled", false);
      $("#workType > option:nth-child(7)").prop("disabled", false);
      $("#workType > option:nth-child(8)").prop("disabled", false);
      $("#workType > option:nth-child(9)").prop("disabled", false);
      $("#workType > option:nth-child(10)").prop("disabled", false);

    }

  });


  // 初期化


  $('#month').attr('min', moment().subtract(12, 'months').format('YYYY-MM'));
  $('#month').attr('max', moment().format('YYYY-MM'));
  $('#week').attr('min', moment().subtract(12, 'weeks').format('YYYY-MM-DD'));
  $('#week').attr('max', moment().format('YYYY-MM-DD'));

  $('#Subordinate').change(function() {
    $.post('report/option', {
      'id': $('#Subordinate').val()
    }).done(function(r) {
      if (!r || r.statusCode === 500) {
        alert(r.reason);

        return false;
      }
      console.log(r);
      $('#option1').attr('min', moment(r.data).format('YYYY-MM-DD'));
    });
  });


  $('#option1').click(function() {
    $('#option1').attr('max', $('#option2').val());
  });
  $('#option2').click(function() {
    $('#option2').attr('min', $('#option1').val());
  });



  $.getJSON('report/login?loginuser=me').done(function(r1) {

    if (!r1 || r1.statusCode === 500) {
      alert(r1.reason);
      location.href = 'index.html?sc=701';
      return false;
    }
    console.log(r1.data);
    $('#loginuser').text(r1.data.name).attr('data-id', r1.data.id).attr('data-positionId', r1.data.positionId);
    $("#month").prop("disabled", false);
    $("#week").prop("disabled", true);
    $("#option1").prop("disabled", true);
    $("#option2").prop("disabled", true);


  });

  $.when(

    $.getJSON('report/emp', {
      'emplist': 'true'
    }),
    $.getJSON('report/work'),
    $.getJSON('report/option')
  ).done(function(r2, r3, r4) {
    r2 = r2[0];
    if (!r2 || r2.statusCode === 500) {
      alert(r2.reason);
      location.href = 'index.html?sc=701';
      return false;
    }
    list(r2.data, 'emp');

    r3 = r3[0];
    if (!r3 || r3.statusCode === 500) {
      alert(r3.reason);
      return false;
    }
    list(r3.data, 'work');

    if (!r4 || r4.statusCode === 500) {
      alert(r4.reason);
      return false;
    }
    console.log(r4);
    $('#option1').attr('min', r4[0].data);
    $('#option2').attr('min', r4[0].data);

  });
});
