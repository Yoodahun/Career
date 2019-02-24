moment.lang('ja', {
  weekdays: ["日曜日", "月曜日", "火曜日", "水曜日", "木曜日", "金曜日", "土曜日"],
  weekdaysShort: ["日", "月", "火", "水", "木", "金", "土"],
});

var m = moment(); //現在時刻
//var m = moment("2018-06-03 17:59"); //日曜日
//var m = moment("2018-06-11 08:00"); //平日9時前
//var m = moment("2018-06-11 09:00"); //平日9時ちょうど
// var m = moment("2018-06-11 09:01"); //平日9時1分

function list(values) {
  for (let key in values) {
    const d = values[key];
    const name = [d.name];
    const id = [d.id];
    console.log(name);
    console.log(id)
    $('#workType').append($('<option>').text(name).val(id));

  }
}

$(function() {
  $('form').submit(function() {
    return false;
  });
  $('button.insert').click(function() {
    $('#dialogLabel').text('入力');
    $('#method').val('post');
    $('form')[0].reset();
    const h = m.hours();
    const t = m.minutes();
    const f = m.format("dddd");
    if (h > 9 || t >= 1) {
      $("#workType").val(2);
    }
    if (f === "土曜日" || f === "日曜日") {
      $('#workType').val(4);
    }
    $('#dialog').modal('show');
  });

  $('table > tbody').on('click', 'th', function() {
    if ($('#start').text() === '--:--') {
      alert('勤怠入力ボタンで入力してください。');
      return false;
    }
    $('#dialogLabel').text('変更');
    $('#method').val('put');
    // const $tr = $(this);
    // $tr.addClass('bg-info');
    // const d = $table.row('.bg-info').data();

    /* 勤務種類 selectのvalueによって選択できないように*/
    $('#workType').val($('#type').val());
    if ($('#type').val() === '5' || $('#type').val() === '6' ||
      $('#type').val() === '7' || $('#type').val() === '8' ||
      $('#type').val() === '9' || $('#type').val() === '10') {
      $('#startTime').prop('disabled', true);
      $('#endTime').prop('disabled', true);
    } else {
      $('#startTime').prop('disabled', false);
      $('#endTime').prop('disabled', false);
      $('#startTime').val($('#start').text());
      $('#endTime').val($('#end').text());
    }
    $('#dialog').modal('show');
  });

  $('button.ok').click(function() {
    const params = $('form').serializeArray();
    if ($('#workType').val() === '1' || $('#workType').val() === '2' ||
      $('#workType').val() === '3' || $('#workType').val() === '4') {
      if ($('#startTime').val() === '') {
        alert('出社時間を入力して下さい。');
        return false;
      }
    }
    if ($('#workType').val() === '5' || $('#workType').val() === '6' ||
      $('#workType').val() === '7' || $('#workType').val() === '8' ||
      $('#workType').val() === '9' || $('#workType').val() === '10') {
      params[2] = {
        'name': 'date',
        'value': m.format('YYYY-MM-DD')
      };
      params[3] = {
        'name': 'id',
        'value': $('#userId').val()
      };
    } else {
      params[4] = {
        'name': 'date',
        'value': m.format('YYYY-MM-DD')
      };
      params[5] = {
        'name': 'id',
        'value': $('#userId').val()
      };
      if (params[2].value === "") {} else {
        if (params[1].value > params[2].value) {
          console.log(params);
          alert('退社時間は出社時間より早く設定することはできません。');
          return false;
        }
      }
    }
    console.log(params);
    $.post(
      'report/record', params
    ).done(function(r) {
      console.log(r);
      if (!r || r.statusCode === 500) {
        alert(r.reason);
        return false;
      }
      $('#start').text(r.data.startTime);
      $('#end').text(r.data.endTime);
      $('#type').text(r.data.workTypeName).val(r.data.workId);
      $('#dialog').modal('hide');
    });
  });

  $('#password').click(function() {
    $('#passDialogLabel').text('パスワード変更');
    $('#passmethod').val('put');
    $('#id').prop('readonly', false);
    $('form')[0].reset();
    $('#passDialog').modal('show');
  });

  $('#esc').click(function() {
    //  $('.Pass').removeClass('bg-primary');
    //  $('.newPass').removeClass('bg-primary');
    //  $('.newPass2').removeClass('bg-primary');
    $('#pass').val('');
    $('#newPass').val('');
    $('#newPass2').val('');
  });

  /* 勤務種類 selectのvalueによって選択できないように*/
  $('#workType').change(function() {
    if ($(this).val() === '5' || $(this).val() === '6' ||
      $(this).val() === '7' || $(this).val() === '8' ||
      $(this).val() === '9' || $(this).val() === '10') {
      $('#startTime').prop('disabled', true);
      $('#endTime').prop('disabled', true);
    } else {
      $('#startTime').prop('disabled', false);
      $('#endTime').prop('disabled', false);
    }
  });

  $('button.change').click(function() {
    const params = $('.pass').serializeArray();
    params[3] = {
      'name': 'id',
      'value': $('#userId').val()
    };
    const pass1 = $('#pass').val();
    const pass2 = $('#newPass').val();
    const pass3 = $('#newPass2').val();
    console.log(params);


    const num = pass2.search(/[0-9]/g);
    const eng = pass2.search(/[a-z]/ig);
    const spe = pass2.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    // if (num < 0 || eng < 0 || spe < 0 ) {
    //   alert("アルファベット、数字、特殊文字を一つ以上合わせて入力して下さい。");　
    //   return false;
    // }


    // if (pass2.length < 8 || pass2.length > 32) {
    //   alert('8文字以上32文字以下で入力して下さい。');
    //   return false;
    // }


      if (pass1 === "" || pass2 === "") {
        alert('空欄があります');
        return false;
      }

      if (!(pass2 === pass3)) {
        alert('新しいパスワードの不一致');
        return false;
      }

      if (pass2.length < 8 || pass2.length > 32) {
        alert('8文字以上32文字以下で入力して下さい。');
        return false;
      }

      if (pass2.match(/^[\u0020-\u007e]+$/) && pass3.match(/^[\u0020-\u007e]+$/))
      {
        alert('アルファベット、数字、特殊文字を合わせてを入力して下さい。');
        return false;

      }
       else
      {
        return false;
      }



    $.post(
      'report/password', params
    ).done(function(r) {
      console.log(r);
      if (!r || r.statusCode === 500) {
        alert(r.reason);
        return false;
      }
      if (r.data === true) {
        location.href = 'index.html';
      } else {
        return false;
      }
    });
  });

  $('#record').click(function() {
    location.href = 'reading.html';
  });

  $('#depart').click(function() {
    location.href = 'depart.html';
  });

  $('#section').click(function() {
    location.href = 'section.html';
  });

  $('#position').click(function() {
    location.href = 'position.html';
  });

  $('#work').click(function() {
    location.href = 'work.html';
  });

  $('#emp').click(function() {
    location.href = 'employee.html';
  });

  $('#workType').click(function() {
    const d = m.format("dddd");
    const h = m.hours();
    const min = m.minutes();
    // console.log(f);
    if (d === "土曜日" || d === "日曜日") {
      $("option:nth-child(1)").prop("disabled", true);
      $("option:nth-child(2)").prop("disabled", true);
      $("option:nth-child(3)").prop("disabled", true);
      $("option:nth-child(4)").prop("disabled", false);
      $("option:nth-child(5)").prop("disabled", false);
      $("option:nth-child(6)").prop("disabled", true);
      $("option:nth-child(7)").prop("disabled", true);
      $("option:nth-child(8)").prop("disabled", true);
      $("option:nth-child(9)").prop("disabled", false);
      $("option:nth-child(10)").prop("disabled", true);
      $("option:nth-child(11)").prop("disabled", true);
    } else {
      if ((h > 9 || min >= 1) && h < 18) {
        $("option:nth-child(1)").prop("disabled", true);
      } else {
        $("option:nth-child(1)").prop("disabled", false);
      }
      $("option:nth-child(2)").prop("disabled", false);

      if (h >= 18 && min > 0) {
        $("option:nth-child(3)").prop("disabled", true);
      } else {
        $("option:nth-child(3)").prop("disabled", false);
      }
      $("option:nth-child(4)").prop("disabled", true);
      $("option:nth-child(5)").prop("disabled", true);
      $("option:nth-child(6)").prop("disabled", false);
      $("option:nth-child(7)").prop("disabled", false);
      $("option:nth-child(8)").prop("disabled", false);
      $("option:nth-child(9)").prop("disabled", false);
      $("option:nth-child(10)").prop("disabled", false);

    }
  });

  $('#startTime').click(function() {
    const f = m.format('HH:mm')
    if ($('#startTime').val() === "") {
      $('#startTime').val(f);
    }
    $('#startTime').attr('min', f);
  });

  $('#endTime').click(function() {
    console.log($('#startTime').val());
    if ($('#endTime').val() === '') {
      $('#endTime').val($('#startTime').val());
    }
    $('#endTime').attr('min', $('#startTime').val());
  });

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


  const date = {
    'date': m.format('YYYY-MM-DD')
  };
  const output = m.format("YYYY年MM月DD日");
  $('#today').text(output);

  // 初期画面処理
  $.getJSON('report/login?loginuser=me').done(function(r1) {
    if (!r1 || r1.statusCode === 500) {

      alert(r1.reason);
      location.href = 'index.html?sc=701';
      return false;
    }
    $('#loginuser').text(r1.data.name);
    $('#userId').text(r1.data.id).val(r1.data.id);
    if (r1.data.positionId === 4) {
      $("#depart").prop("disabled", true);
      $("#section").prop("disabled", true);
      $("#position").prop("disabled", true);
      $("#work").prop("disabled", true);
      $("#emp").prop("disabled", true);
    }
  });
  //comment
  $.when(
    $.get('report/record', date),
    $.get('report/work')
  ).done(function(r2, r3) {
    r2 = r2[0];
    if (!r2 || r2.statusCode === 500) {
      alert(r2.reason);
      return false;
    }
    console.log(r2);
    if (r2.data !== null) {
      $('#start').text(r2.data.startTime);
      $('#end').text(r2.data.endTime);
      $('#type').text(r2.data.workTypeName).val(r2.data.workId);
    }


    r3 = r3[0]
    if (!r3 || r3.statusCode === 500) {
      alert(r3.reason);
      return false;
    }
    list(r3.data);
  });
});
