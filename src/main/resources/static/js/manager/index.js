 $(document).ready(function(){
    	 $.ajax({	
    		type:"GET",
 	    	contentType: "false",
 	    	url:"/graph",
 	   		success: function(result){ 
		      var pdata = [
		      	{
		      		value: result.numberOfDownloadMath,
		      		color: "#46BFBD",
		      		highlight: "#5AD3D1",
		      		label: "Toán học"
		      	},
		      	{
		      		value: result.numberOfDownLoadPhysical,
		      		color:"#F7464A",
		      		highlight: "#FF5A5E",
		      		label: "Vật lí"
		      	},
		      	{
		      		value: result.numberOfDownLoadChemistry,
		      		color:"#339966",
		      		highlight: "#669966",
		      		label: "Hóa học"
		      	}
		      ]
		      
		      
		      var ctxp = $("#pieChartDemo").get(0).getContext("2d");
		      var pieChart = new Chart(ctxp).Pie(pdata);
		 	}
    	 });
     });