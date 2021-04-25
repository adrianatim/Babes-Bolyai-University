<!DOCTYPE html>
<html>
	<head>
    <script
    src="https://code.jquery.com/jquery-2.0.3.js"
    integrity="sha256-lCf+LfUffUxr81+W0ZFpcU0LQyuZ3Bj0F2DQNCxTgSI="
    crossorigin="anonymous">
    </script>
        <script>
            $(document).ready(function(){
                let pageNumber = 0;
                $("#next").click(function(){
                    pageNumber += 1;
                    $.ajax({
                        type: "GET",
                        url : "getElements.php",
                        data: {pageNo: pageNumber},
                        success: function(data, status){
                            $("#main").html(data);
                        }
                    });
                });
                $("#prev").click(function(){
                    pageNumber -= 1;
                    $.ajax({
                        type: "GET",
                        url : "getElements.php",
                        data: {pageNo: pageNumber},
                        success: function(data, status){
                            $("#main").html(data);
                        }
                    });
                });
            });
        </script>

    <style>
        body{ font: 14px sans-serif; text-align: center; }
        .button {
            border: none;
            color: white;
            padding: 16px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            transition-duration: 0.4s;
            cursor: pointer;
        }

        .button1 {
            background-color: white; 
            color: black; 
            border: 2px solid #a88beb;
        }

        .button1:hover {
            background-color: #a88beb;
            color: white;
        }
    </style>
    </head>
	<body>
    <div id="main"></div>
    <button id="prev" class="button button1">Prev</button>
    <button id="next" class="button button1">Next</button>
	</body>
</html>