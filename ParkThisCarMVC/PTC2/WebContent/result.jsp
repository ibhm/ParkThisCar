<%@ page import = "java.util.*" %>
<html>
<body>
<h1 align="center">Results</h1>
<p>

<% 

out.print("Here are your results:<br>");

ArrayList<String> spots = (ArrayList<String>)request.getAttribute("spaces");
//List spots = (List)request.getAttribute("spaces");
System.out.println(spots);
//out.print(spots); 
for(int i = 0; i < spots.size(); i++){
  out.print("<br>TRY :  " + spots.get(i) + "<br>");
}


//List new2 = (ArrayList<String>)spots;
//List locations = (List)request.getAttribute("spaces");
//Iterator<String> it = spots.iterator();
//while(it.hasNext()){
//	out.print("<br>TRY:   " + it.next());
//	} 
/*
List [parking spaces] = (List)request.getAttribute("spaces");
Iterator it=spaces.iterator();
while(it.hasNext()){
	out.print("<br>TRY:" + it.next());
	} 
This produces a list of parking spaces, and prints out each spot, one by one as an iteration
*/
%>

</body>
</html>