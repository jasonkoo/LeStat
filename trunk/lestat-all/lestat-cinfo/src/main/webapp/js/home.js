var projectPrefix = "/lestat-cinfo/"

$(function(){
      $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
      // 获取已激活的标签页的名称
      var activeTab = $(e.target).text(); 
      // 获取前一个激活的标签页的名称
      var previousTab = $(e.relatedTarget).text(); 
      $(".active-tab span").html(activeTab);
      $(".previous-tab span").html(previousTab);
      $("#viewTitle").text(activeTab);
   });
});

$(document).ready(function() { 
	//alert("asdasfd");
	$("#viewTitle").text("Host");
	mytree("#viewContent", projectPrefix + "view");
	$("#viewTopMenu").find("a[data-url]").click(function(ev) {
		  //console.log("aaa" + $(this).attr("data-url"));
		  //$("#viewTitle").text(activeTab);
		  mytree("#viewContent",  projectPrefix + $(this).attr("data-url"));
	}); 
	$("#adminTopMenu").find("a").click(function(ev) {
		  //console.log("aaa" + $(this).attr("data-url"));
		  //alert($(this).text());
		  //mytree("#viewContent", "${pageContext.request.contextPath}/" + $(this).attr("data-url"));
		  //var nowTime = new Date(); 
          //var nMS=nowTime.getTime();
          //alert("aaa: " + nMS);
		  updateAdminContent(projectPrefix + $(this).attr("data-url"));
	}); 
	$("#linkTopMenu").find("a[data-url]").click(function(ev) {
		  //console.log("aaa" + $(this).attr("data-url"));
		  //alert($(this).attr("href"));
		  $(this).attr('target', '_blank');
		  window.open($(this).attr("data-url")); 
		  $("#viewTitle").text($(this).attr("data-url"));
		  $("#viewContent").empty();
		  //return false;
		  //mytree("#viewContent", "${pageContext.request.contextPath}/" + $(this).attr("data-url"));
	}); 
	$("#a2login").click(function(ev) {
		  //console.log("aaa" + $(this).attr("data-url"));
		  //alert($(this).attr("href"));
		  document.location.href= projectPrefix + "login";
	}); 
});


function updateAdminContent(dataUrl) {
	$("#viewContent").empty();
	var objectData = [
	                 // {id: 1, name: "Ted Right", address: ""},
	                 // {id: 2, name: "Frank Honest", address: ""},
	                 // {id: 3, name: "Joan Well", address: ""}
	                ];

	var $container = $("<div />");
	
	var saveRenderer = function (instance, td, row, col, prop, value, cellProperties) {
          $(td).empty().html(value); //empty is needed because you are rendering to an existing cell
          cellProperties.readOnly = true;
          $(td).find("button").click(function(ev) {
        	  saveRow(td);
        	  //alert($(td).parent().find("td").first().html());
    	}); 
          $(td).find("button").css("margin-left","13px");
          $(td).find("button").css("margin-right","13px");
          $(td).find("button").css("margin-top","3px");
          $(td).find("button").css("margin-bottom","3px");
		  return td;
		};
	
    var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	function saveRow(td){
		alert($(td).parent().html());
		$.ajax({
	        url: projectPrefix + "admin/save",
	        contentType: 'application/json',
	        dataType: "json",
	        type: "POST",
	        beforeSend: function(xhr) { 
	              xhr.setRequestHeader(header, token); 
	            },
	        data: {"id":3,"name":"name3","address":"address6"}, //contains changed cells' data
	        success: function (res) {
  	          //handsontable.loadData(res.data);
  	          //alert(res.message);
  	           myShowToast(res.message,"result","success",true);
  	          //$console.text('Data loaded');
  	           updateAdminContent(dataUrl);
  	        }
	      });	
	}
		
    function deleteRow(td){
    	alert($(td).parent().find("td").first().html());
    	$.ajax({
    	        url: projectPrefix + "admin/remove?name=kafka&id=3",
    	        dataType: 'json',
    	        type: 'GET',
    	        success: function (res) {
    	          //handsontable.loadData(res.data);
    	          //alert(res.message);
    	           myShowToast(res.message,"result","success",true);
    	          //$console.text('Data loaded');
    	           updateAdminContent(dataUrl);
    	        }
    	      });
    }
		
    var removeRenderer = function (instance, td, row, col, prop, value, cellProperties) {
	          $(td).empty().html(value); //empty is needed because you are rendering to an existing cell
	          cellProperties.readOnly = true;
	          $(td).find("button").click(function(ev) {
	    		  
	    		  deleteRow(td);
	    		  //updateAdminContent("aaa");
	    		  
	    	}); 
	          $(td).find("button").css("margin-left","13px");
	          $(td).find("button").css("margin-right","13px");
	          $(td).find("button").css("margin-top","3px");
	          $(td).find("button").css("margin-bottom","3px");
			  return td;
			};
		
	$container.handsontable({
            data: objectData,
            //startRows: 5,
            //startCols: 3,
            currentRowClassName: 'currentRow',
            currentColClassName: 'currentCol',
            colHeaders: true,
            afterChange: function (changes, source) {
                if (source !== 'loadData') {
                  $("#test-div").text(JSON.stringify(changes));
                }
              },
            className: "htCenter htMiddle",
            colHeaders: ['id', 'name', 'email', 'save','remove'],
            columns: [
                      {
                        data: "id",
                        readOnly: true
                      },
                      {
                        data: "name"
                      },
                      {
                        data: "address"
                      },
                      {
                          data: "save",renderer: saveRenderer
                        }
                      ,
                      {
                          data: "remove",renderer: removeRenderer
                        }
                    ],
            minSpareRows: 1
        });

    handsontable = $container.data('handsontable');
    
    $.ajax({
        url: dataUrl,
        dataType: 'json',
        type: 'GET',
        success: function (res) {
          handsontable.loadData(res.data);
          //$console.text('Data loaded');
        }
      });

    //handsontable.loadData(objectData);
    $("#viewContent").html($container);
    $("#viewContent").append(getPagination(dataUrl, 1, 5));
}

function mytree(id, url) {
var m = [20, 120, 20, 120],
    w = 1280 - m[1] - m[3],
    h = 800 - m[0] - m[2],
    i = 0,
    root;

var tree = d3.layout.tree()
    .size([h, w]);

var diagonal = d3.svg.diagonal()
    .projection(function(d) { return [d.y, d.x]; });

    $(id).empty();
var vis = d3.select(id).append("svg:svg")
    .attr("width", w + m[1] + m[3])
    .attr("height", h + m[0] + m[2])
  .append("svg:g")
    .attr("transform", "translate(" + m[3] + "," + m[0] + ")");

d3.json(url, function(json) {
	url1 = url;
  root = json;
  root.x0 = h / 2;
  root.y0 = 0;

  function toggleAll(d) {
    if (d.children) {
      d.children.forEach(toggleAll);
      toggle(d);
    }
  }

  // Initialize the display to show a few nodes.
  root.children.forEach(toggleAll);
//   toggle(root.children[1]);
//   toggle(root.children[1].children[2]);
//   toggle(root.children[9]);
//   toggle(root.children[9].children[0]);

  update(root);
});


function update(source) {
	  var duration = d3.event && d3.event.altKey ? 5000 : 500;

	  // Compute the new tree layout.
	  var nodes = tree.nodes(root).reverse();

	  // Normalize for fixed-depth.
	  nodes.forEach(function(d) { d.y = d.depth * 180; });

	  // Update the nodes…
	  var node = vis.selectAll("g.node")
	      .data(nodes, function(d) { return d.id || (d.id = ++i); });

	  // Enter any new nodes at the parent's previous position.
	  var nodeEnter = node.enter().append("svg:g")
	      .attr("class", "node")
	      .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
	      .on("click", function(d) { toggle(d); update(d); });

	  nodeEnter.append("svg:circle")
	      .attr("r", 1e-6)
	      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

	  nodeEnter.append("svg:text")
	      .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
	      .attr("dy", ".35em")
	      .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
	      .text(function(d) { return d.name; })
	      .style("fill-opacity", 1e-6);

	  // Transition nodes to their new position.
	  var nodeUpdate = node.transition()
	      .duration(duration)
	      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

	  nodeUpdate.select("circle")
	      .attr("r", 4.5)
	      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

	  nodeUpdate.select("text")
	      .style("fill-opacity", 1);

	  // Transition exiting nodes to the parent's new position.
	  var nodeExit = node.exit().transition()
	      .duration(duration)
	      .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
	      .remove();

	  nodeExit.select("circle")
	      .attr("r", 1e-6);

	  nodeExit.select("text")
	      .style("fill-opacity", 1e-6);

	  // Update the links…
	  var link = vis.selectAll("path.link")
	      .data(tree.links(nodes), function(d) { return d.target.id; });

	  // Enter any new links at the parent's previous position.
	  link.enter().insert("svg:path", "g")
	      .attr("class", "link")
	      .attr("d", function(d) {
	        var o = {x: source.x0, y: source.y0};
	        return diagonal({source: o, target: o});
	      })
	    .transition()
	      .duration(duration)
	      .attr("d", diagonal);

	  // Transition links to their new position.
	  link.transition()
	      .duration(duration)
	      .attr("d", diagonal);

	  // Transition exiting nodes to the parent's new position.
	  link.exit().transition()
	      .duration(duration)
	      .attr("d", function(d) {
	        var o = {x: source.x, y: source.y};
	        return diagonal({source: o, target: o});
	      })
	      .remove();

	  // Stash the old positions for transition.
	  nodes.forEach(function(d) {
	    d.x0 = d.x;
	    d.y0 = d.y;
	  });
	}

	// Toggle children.
	function toggle(d) {
	  if (d.children) {
	    d._children = d.children;
	    d.children = null;
	  } else {
	    d.children = d._children;
	    d._children = null;
	  }
	}
}

/*function getPagination(){
	var p = '<nav><ul class="pagination pagination-sm" style="margin:0"><li><a href="#"><span class="glyphicon glyphicon-fast-backward"></span></a></li><li><a href="#"><span class="glyphicon glyphicon-backward"></span></a></li><li><input type="text" value="1" style="position: relative; float: left; width: 24px;padding-bottom: 0px;padding-top: 0px;"/></li><li><a href="#"><span class="glyphicon glyphicon-play" aria-hidden="true"></span></a></li><li><a href="#"><span class="glyphicon glyphicon-forward"></span></a></li><li><a href="#"><span class="glyphicon glyphicon-fast-forward"></span></a></li></ul></nav>';
	return p;
}
*/

function getPagination(dataurl, currentPageNumber, totalPageNumber) {
	var fblink = dataurl + '&p=1';
	var blink = dataurl + '&p=' + ((currentPageNumber - 1) < 1 ? 1 : (currentPageNumber - 1));
	var curlink = dataurl + '&p=' + currentPageNumber;
	var flink = dataurl + '&p=' + ((currentPageNumber + 1) > totalPageNumber ? totalPageNumber : (currentPageNumber + 1));
	var fflink = dataurl + '&p=' + totalPageNumber;
	
	var p = '<nav><ul class="pagination pagination-sm" style="margin:0">';
	p += '<li class="!"><a href="%"><span class="glyphicon glyphicon-fast-backward"></span></a></li>';
	if (currentPageNumber > 1) {
		p = p.replace(/%/, fblink);
		p = p.replace(/!/, "");
	} else {
		p = p.replace(/%/, "#");
		p = p.replace(/!/, "disabled");	 
	}
	p += '<li class="!"><a href="%"><span class="glyphicon glyphicon-backward"></span></a></li>';
	if (currentPageNumber > 1) {
		p = p.replace(/%/, blink);
		p = p.replace(/!/, "");
	} else {
		p = p.replace(/%/, "#");
		p = p.replace(/!/,"disabled");	 
	}
	
	p += '<li><input type="text" value="%" style="position: relative; float: left; width: 24px;padding-bottom: 0px;padding-top: 0px;"/></li>';
	p = p.replace(/%/, currentPageNumber);
	p += '<li><a href="%"><span class="glyphicon glyphicon-play" aria-hidden="true"></span></a></li>';
	p = p.replace(/%/, curlink);

	p += '<li class="!"><a href="%"><span class="glyphicon glyphicon-forward"></span></a></li>';
	if (currentPageNumber < totalPageNumber){
		p = p.replace(/%/, flink);
		p = p.replace(/!/, "");
	} else {
		p = p.replace(/%/, "#");
		p = p.replace(/!/,"disabled");	 
	}
	p += '<li class="!"><a href="%"><span class="glyphicon glyphicon-fast-forward"></span></a></li></ul></nav>';
	if (currentPageNumber < totalPageNumber){
		p = p.replace(/%/, fflink);
		p = p.replace(/!/, "");
	} else {
		p = p.replace(/%/, "#");
		p = p.replace(/!/,"disabled");
	}
	

	return p;
}