

			function openNav() {
			  document.getElementById("mySidenav").style.width = "250px";
			  document.getElementById("main").style.marginLeft = "250px";
			  document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
			}

			function closeNav() {
			  document.getElementById("mySidenav").style.width = "0";
			  document.getElementById("main").style.marginLeft= "0";
			  document.body.style.backgroundColor = "white";
			}
			
			

/****************INIT***************/
		var natures=[];
		var nid;
		$(document).ready(function() {
		    $(".call-btn").click(function(){
		    	$(this).text($(this).text() == "Back to Dashboard" ? "Hide Dashboard" : "Back to Dashboard");
		    	$("#dashboard").slideToggle("slow");
		    });
		    
		  $("#success-alert").hide();
		  $("#error-alert").hide();
		  $("#alert-doccheck").hide();
		  
		  $("#man-success").hide();
		  $("#man-error").hide();
		  
		  $("#reg-success").hide();
		  $("#reg-error").hide();
		  
		  $("#dashboard-success-alert").hide();
		  

		  $("#btnPrint").click(function(){
		  	 var divContents = $("#dvContainer").html();
		  	    var printWindow = window.open('', '', 'height=400,width=800');
		  	    printWindow.document.write(divContents);            
		  	    printWindow.document.close();
		  	    printWindow.print();
		  });
		    
		});

	/******Close Document Instructions********/
	function closeInstruction() {
		  var x = document.getElementById("docInstruction");
		    x.style.display = "none";
		}

	function printTable() {
	    var mywindow = window.open();
	    var dvContainer = document.getElementById('dvContainer');
	    var is_chrome = Boolean(mywindow.chrome);
	    mywindow.document.write(dvContainer.outerHTML);
	   if (is_chrome) {
	     setTimeout(function() { // wait until all resources loaded 
	        mywindow.document.close(); // necessary for IE >= 10
	        mywindow.focus(); // necessary for IE >= 10
	        mywindow.print(); // change window to winPrint
	        mywindow.close(); // change window to winPrint
	     }, 200);
	   	} else {
	        mywindow.document.close(); // necessary for IE >= 10
	        mywindow.focus(); // necessary for IE >= 10
	        mywindow.print();
	        mywindow.close();
	   	}
	    return true;
	}
	
	function isRequestFor(date){
//		const today  = new Date();
//		var mmCurrent = String(today.getMonth() + 1).padStart(2, '0'); 
//		var yyyyCurrent = today.getFullYear();
//		const foundd = new Date(date);
//		var mmFound = String(foundd.getMonth() + 1).padStart(2, '0'); 
//		var yyyyfound = foundd.getFullYear();
//		if(yyyyCurrent>=yyyyfound){
//			return false;
//		}else{
//			return false;
//		}
		return false;
	}

/**********Table Data Search*************/

$(document).ready(function(){
  $("#mysearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#mysearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#mymansearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#mymansearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#mydelsearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#mydelsearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#myrepsearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myressearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#mypacksearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#mypackssearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#mymanvsearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myvressearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#mymanitemsearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#mymanitemsearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
   $("#mydelitemsearchInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#mydelitemsearchTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  
});
