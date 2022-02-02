$(document).on('click','#notification' ,function(){
   $(".notificationDiv").html('');
   $.ajax({
    url: "fetchNotification",
    type:"POST",
    success: function(result){
    console.log(JSON.stringify(result))
      for(let key in result){
        $(".notificationDiv").append('<div class="card shadow col-12  p-2 m-2" style="max-width: 246px;">'+
                                      '<div class="row d-flex col-12">'+
                                          '<div class="col-4">'+
                                              '<p data-letters="A">Admin</p>'+
                                          '</div>'+
                                          '<div class="col-8 notification-div p-0">'+
                                              '<p class="notificationHeading"><b>'+result[key].notificationHeading+'</b></p>'+
                                              '<p class="notificationSubHeading">'+result[key].notificationSubHeading+'</p>'+
                                              '<p  class="notificationDateTime"><small>'+result[key].notificationDateTime+'</small></p>'+
                                          '</div>'+
                                      '</div>'+
                                  '</div>');
      }
    }
    })
});