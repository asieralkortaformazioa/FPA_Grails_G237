
<script lang="text/javascript">

function toJson( selector ) {
        var o = {};
        $.map( $( selector ), function( n,i )
        {
            o[n.name] = $(n).val();
        });
        return o;
    }


function doSaveAdjustmentFactor ()
{

//    if (adjustmentsValid()) {

    //    validateAfs();
            var json = toJson("pageForm");
    //    $("#pageForm").setAttribute("action","saveAdjustmentFactor");
            var idProj = $("#projects").val();

            var data= {}

//            document.getElementById("pageForm").action = "saveAdjustmentFactor";

            var url = "<g:createLink controller="adjustmentFactor" action="saveAdjustmentFactor" />"; //
//            var url = getAdjustmentFactorUrl();
            alert(url);
            var params = {projects:idProj};
            for (var i =1;i<15;i++ )
            {
                var propName = "af"+i+"Count";
                params[propName]=document.getElementById(propName).value;

            }
            var json = JSON.stringify(params);

            $.post(url, { data: json,
                done: function (result) {
                    alert("Success Adjustment");
                },
                fail: function (data) {
                    alert("Error:" + data);
                }
            });
//    }
//    else{
//        alert ("There are invalid adjustments...");
//    }
}

function adjustmentsValid ()
{
    var valid=true;
    for (var i=1; i<=14; i++)
    {
        valid = valid && $("#helpAf"+i).valid();
    }
    return valid ;

}
function validateAfs ()
{
    jQuery.validator.setDefaults({
        debug: true,
        success: "valid"
    });
    for (var i =1; i<=14; i++)
    {
        $("#helpAf"+i).validate ({
                rules: {
                    field: {
                        required: true,
                        number: true
                    }
             }
        }
        );
    }
}

    function configAdjustment () {
//        alert ("ready2");
        validateAfs ();

        $("#AdjustemntFactorVisible").hide();
        $("#AdjustemntFactorHidden").show();

        $("#helpAdjustemntFactor").hide();
        $("#helpAf1").hide();
        $("#helpAf2").hide();
        $("#helpAf3").hide();
        $("#helpAf4").hide();
        $("#helpAf5").hide();
        $("#helpAf6").hide();
        $("#helpAf7").hide();
        $("#helpAf8").hide();
        $("#helpAf9").hide();
        $("#helpAf10").hide();
        $("#helpAf11").hide();
        $("#helpAf12").hide();
        $("#helpAf13").hide();
        $("#helpAf14").hide();
        $("#helpAdjustemntFactor").hide();


        $("#txtAdjustmentFactor").click(function () {
            if ($("#helpAdjustemntFactor").is(":hidden"))
                $("#helpAdjustemntFactor").show();
            else
                $("#helpAdjustemntFactor").hide();
        });

        $("#af1").click(function () {
            if ($("#helpAf1").is(":hidden"))
                $("#helpAf1").show();
            else
                $("#helpAf1").hide();
        });

        $("#af2").click(function () {
            if ($("#helpAf2").is(":hidden"))
                $("#helpAf2").show();
            else
                $("#helpAf2").hide();
        });

        $("#af3").click(function () {
            if ($("#helpAf3").is(":hidden"))
                $("#helpAf3").show();
            else
                $("#helpAf3").hide();
        });

        $("#af4").click(function () {
            if ($("#helpAf4").is(":hidden"))
                $("#helpAf4").show();
            else
                $("#helpAf4").hide();
        });

        $("#af5").click(function () {
            if ($("#helpAf5").is(":hidden"))
                $("#helpAf5").show();
            else
                $("#helpAf5").hide();
        });

        $("#af6").click(function () {
            if ($("#helpAf6").is(":hidden"))
                $("#helpAf6").show();
            else
                $("#helpAf6").hide();
        });


        $("#af7").click(function () {
            if ($("#helpAf7").is(":hidden"))
                $("#helpAf7").show();
            else
                $("#helpAf7").hide();
        });

        $("#af8").click(function () {
            if ($("#helpAf8").is(":hidden"))
                $("#helpAf8").show();
            else
                $("#helpAf8").hide();
        });

        $("#af9").click(function () {
            if ($("#helpAf9").is(":hidden"))
                $("#helpAf9").show();
            else
                $("#helpAf9").hide();
        });

        $("#af10").click(function () {
            if ($("#helpAf10").is(":hidden"))
                $("#helpAf10").show();
            else
                $("#helpAf10").hide();
        });

        $("#af11").click(function () {
            if ($("#helpAf11").is(":hidden"))
                $("#helpAf11").show();
            else
                $("#helpAf11").hide();
        });

        $("#af12").click(function () {
            if ($("#helpAf12").is(":hidden"))
                $("#helpAf12").show();
            else
                $("#helpAf12").hide();
        });

        $("#af13").click(function () {
            if ($("#helpAf13").is(":hidden"))
                $("#helpAf13").show();
            else
                $("#helpAf13").hide();
        });

        $("#af14").click(function () {
            if ($("#helpAf14").is(":hidden"))
                $("#helpAf14").show();
            else
                $("#helpAf14").hide();
        });

        $("#imgAdjustmentHelp").click(function () {
            if ($("#helpAdjustemntFactor").is(":hidden"))
                $("#helpAdjustemntFactor").show();
            else
                $("#helpAdjustemntFactor").hide();
        });

        //Set validators

        jQuery.validator.setDefaults({
            debug: true,
            success: "valid"
        });

    };


    function getAdjustmentFactorUrl ()
    {
        var idProj = document.getElementById("projects").value;
//        return "../adjustmentFactor/"+idProj;
        return "getAdjustmentFactors/"+idProj;
    }


    function clearAdjustmentFactors ()
    {
            for (var i =1;i<15;i++)
            {
                var elem = document.getElementById ("af"+i+"Count");
                elem.value="";
            }
    }

    function loadAdjustmentFactors (idProj)
    {
        clearAdjustmentFactors ();

        if (idProj!= undefined && idProj!=null && idProj!="null") {
            /* */
            var url = getAdjustmentFactorUrl();
            var resData = null;
            var obj = null;
            console.log("Loading adjustmentFactors for :" + idProj);
            console.log("url:" + url);
            $.get(url, function (data) {

                var res = $.parseJSON(data);
                var i = 0;
                $.each(res.items,
                        function (index, it) {

                            var arr = it;
                            console.log("i:" + i);
                            var responseElem = document.getElementById("af" + (i + 1) + "Count")

                            console.log("arr:" + arr);
                            if (responseElem != null) {
                                responseElem.value = arr.response;
                            }
                            i++;

                        })
            });

        }

    }




function doShowAdjFactor()
{

    if ($("#AdjustemntFactorHidden").is(":hidden"))
    {
        $("#AdjustemntFactorHidden").show();
        $("#AdjustemntFactorVisible").hide();
    }
    else
    {
        $("#AdjustemntFactorHidden").hide();
        $("#AdjustemntFactorVisible").show();
    }
}

</script>

<input type="button" onclick="javascript:doShowAdjFactor();" value="Show/Hide adjustment factor"/>
<div id="AdjustemntFactorHidden"><h2>Adjustment Factor</h2> </div>
<div id="AdjustemntFactorVisible" style="border:2px solid;border-radius:25px; background-color: #8888BB">
    <p id="txtAdjustmentFactor" ><h2>Adjustment Factor <img width="20" height="20" src="../images/help.jpg" id="imgAdjustmentHelp" />:</h2></p>
    <div id="helpAdjustemntFactor">
        <table>
                <thead><th>Score as</th><th>System Influence</th></thead>
                <tr><td>0 </td> <td>Not present or no influence</td></tr>
                <tr> <tD>1</tD> <td>Incidental influence</td></tr>
                <tr><td>2</td> <td>Moderate influence</td></tr>
                <tr><td>3</td><td>Average influence</td> </tr>
                <tr> <td>4</td><td>Significant influence</td></tr>
                <tr><td>5</td> <td>Strong influence throughout</td></tr>
        </table>
    </div>

    <p >1. Data Communications: <img width="20" height="20" src="../images/help.jpg" id="af1" /> <g:textField id="af1Count" name="af1Count" value="${viewBean?.af1Count}"  /></p>
<div id="helpAf1">
    <p>Definition: Data Communications describes the degree to which the application
    communicates directly with the processor.
    The data and control information used in the application are sent or received
    over communication facilities. Devices connected locally to the control unit
    are considered to use communication facilities. Protocol is a set of
    conventions that permit the transfer or exchange of information between two
    systems or devices. All data communication links require some type of
    protocol.
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>Application is batch but has remote data entry or remote printing</td>
        <tr><td>2</td><td>Application is batch but has remote data entry and remote printing</td>
        <tr><td>3</td><td> Application includes on-line data collection or TP
        (teleprocessing) front end to a batch process or query system</td></tr>
        <tr><td>4 </td><td>Application is more than a front-end, but supports only one type
        of TP communications</td></tr>
        <tr><td>5</td><td>Application is more than a front-end, and supports <b>more than
        one</b> type of TP communications protocol</td>
        </tr>
    </table>

    <p>Hints</p>
    <p>
        Protocol examples include FTP, dial-up, Token Ring, Ethernet, SNA, TCP/IP,
        IPX/SPX, HTTP, XML, WAP, NTP, ICQ, and NETBEUI. This list should
        not be considered exhaustive.
        The entry of data does not involve reading or writing directly
        to an ILF.
        • Simple business rules and minimal edits (e.g., alpha/numeric,
        range check, required data, etc.) may be performed. When
        this data is eventually processed by the application,
        additional edits are performed.
        The entry of data does not involve reading or writing directly
        to an ILF. Data are entered on-line, but the transactions are
        stored in a temporary file for batch update of ILF(s) at a later
        time.

        • Data for the application is collected and may directly update
        ILF(s) or be stored for future processing using an input
        device, which performs edits based on business rules.

        • Only one communication protocol is used. Typically, when
        this data is processed by the application, no further edits are
        required.

        • The entry of data involves reading or writing to an ILF.

        • The entry of data does not involve reading or writing directly
        to an ILF. Data are entered on-line, but the transactions are
        stored in a temporary file for batch update of ILF(s) at a later
        time.

        •
        Hints to
        Rule 4
        Remote devices might include a 3270 terminal connected to
        a mainframe computer that allows only simple edits (numeric
        vs. alpha), or printers
        connected via parallel port (the user can specify where to
        direct the
        output).
        •
        Hints to
        Rule 3
        • •
        Hints to
        Rules 1
        and 2
        For example, client-server data entry or Internet data entry,
        but not both.


        Determine Value Adjustment Factor
        Part 1 – Process and Rules
        • Same as 4, however, data collection is performed using
        multiple telecommunication protocols.
        •
        Hints to
        Rule 5
        For example, client-server data entry and Internet data entry
        of the same transaction.
        Batch applications receive a score of 0 to 3
        On-line applications receive a score of 4
        • Web-based applications receive a score of 4 or 5
        •
        8-8
        • •
        <b>Typically</b>
        Real-time, telecommunication, or process control systems receive a score
        of 4 or 5

    </p>
</div>


<p >2. Distributed Data Processing <img width="20" height="20" src="../images/help.jpg" id="af2" />: <g:textField id="af2Count" name="af2Count" value="${viewBean?.af2Count}"  /></p>
<div id="helpAf2" >
    <p>Definition: Distributed Data Processing describes the degree to which the application
    transfers data among physical components of the application.
    Distributed data or processing functions are a characteristic of the application
    within the application boundary
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>Data is not transferred or processed on another component of the system.</td></tr>
        <tr><td>1</td><td>Data is prepared for transfer, then is transferred and processed on another component of the system, for user processing.</td></tr>
        <tr><td>2</td><td>Data is prepared for transfer, then is transferred and processed on another component of the system , <b>not</b> for user processing.</td></tr>
        <tr><td>3</td><td>Distributed processing and data transfer are on-line and in <b>one</b> direction only.</td></tr>
        <tr><td>4</td><td>Distributed processing and data transfer are on-line and in <b>both</b> directions</td></tr>
        <tr><td>5</td><td>Distributed processing and data transfer are on-line and are dynamically performed on the most appropriate component of the system.</td></tr>

    </table>
</div>

<p >3. Performance <img width="20" height="20" src="../images/help.jpg" id="af3" />: <g:textField id="af3Count" name="af3Count" value="${viewBean?.af3Count}"  /></p>
<div id="helpAf3" >
    <p>Definition: Performance describes the degree to which response time and throughput
    performance considerations influenced the application development.
    Application performance objectives, stated or approved (or implied) by the
    user, in either response or throughput, influence (or will influence) the design,
    development, installation, and support of the application.
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>No special performance requirements were stated by the user..</td></tr>
        <tr><td>1</td><td>Performance and design requirements were stated and reviewed but no special actions were required.</td></tr>
        <tr><td>2</td><td>Response time or throughput is critical during <b>peak</b> hours. No special design for CPU utilization was required. Processing deadline is for the next business cycle.</td></tr>
        <tr><td>3</td><td>Response time or throughput is critical during  <b>all business</b> hours. No special design for CPU utilization was required. Processing deadline requirements with interfacing systems are constraining.</td></tr>
        <tr><td>4</td><td>In addition, stated user performance requirements are stringent enough to require performance analysis tasks in the design phase.</td></tr>
        <tr><td>5</td><td>In addition, performance analysis tools were used in the design, development, and/or implementation phases to meet the stated user performance requirements.</td></tr>

    </table>
</div>


<p >4. Heavily Used Configuration <img width="20" height="20" src="../images/help.jpg" id="af4" />: <g:textField id="af4Count" name="af4Count" value="${viewBean?.af4Count}"  /></p>
<div id="helpAf4" >
    <p>Definition: Heavily Used Configuration describes the degree to which computer resource
    restrictions influenced the development of the application.
    A heavily used operational configuration may require special considerations
    when designing the application. For example, the user wants to run the
    application on existing or committed equipment that will be heavily used.
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>No explicit or implicit operational restrictions are included.</td></tr>
        <tr><td>1</td><td>Operational restrictions do exist, but are less restrictive than a typical application. No special effort is needed to meet the restrictions.</td></tr>
        <tr><td>2</td><td>Operational restrictions do exist, but are typical for an application. Special effort through controllers or control programs is needed to meet the restrictions.</td></tr>
        <tr><td>3</td><td>Stated operational restrictions require special constraints on <b>one</b> piece of the application in the central processor or a dedicated processor.</td></tr>
        <tr><td>4</td><td>Stated operational restrictions require special constraints on the <b>entire</b> application in the central processor or a dedicated processor.</td></tr>
        <tr><td>5</td><td>In addition, there are special constraints on the application in the distributed components of the system.</td></tr>

    </table>
</div>

<p >5. Transaction Rate <img width="20" height="20" src="../images/help.jpg" id="af5" />: <g:textField id="af5Count" name="af5Count" value="${viewBean?.af5Count}"  /></p>
<div id="helpAf5" >
    <p>Definition: Transaction Rate describes the degree to which the rate of business
    transactions influenced the development of the application.
    The transaction rate is high, and it influences the design, development,
    installation, and support of the application. Users may require what they
    regard as normal response time even during times of peak volume.
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>No peak transaction period is anticipated.</td></tr>
        <tr><td>1</td><td>Low transaction rates have minimal effect on the design, development, and installation phases</td></tr>
        <tr><td>2</td><td>Average transaction rates have some effect on the design, development, and installation phases.</td></tr>
        <tr><td>3</td><td>High transaction rates affect the design, development, and/or installation phases.</td></tr>
        <tr><td>4</td><td>High transaction rate(s) stated by the user in the application requirements or service level agreements are high enough to require performance analysis tasks in the design, development, and/or installation phases.</td></tr>
        <tr><td>5</td><td>High transaction rate(s) stated by the user in the application requirements or service level agreements are high enough to require performance analysis tasks and, in addition, require the use of performance analysis tools in the design, development, and/or installation phases.</td></tr>

    </table>
</div>
<p >6. Online Data Entry <img width="20" height="20" src="../images/help.jpg" id="af6" />: <g:textField id="af6Count" name="af6Count" value="${viewBean?.af6Count}"  /></p>
<div id="helpAf6" >
    <p>Definition: Online Data Entry describes the degree to which data is entered or retrieved
    through interactive transactions.
    On-line User Interface for data entry, control functions, reports, and queries
    are provided in the application.
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>All transactions are processed in batch mode.</td></tr>
        <tr><td>1</td><td>1% to 7% of transactions are interactive.</td></tr>
        <tr><td>2</td><td>8% to 15% of transactions are interactive.</td></tr>
        <tr><td>3</td><td>16% to 23% of transactions are interactive.</td></tr>
        <tr><td>4</td><td>24% to 30% of transactions are interactive.</td></tr>
        <tr><td>5</td><td>More than 30% of transactions are interactive.</td></tr>

    </table>
</div>
<p >7. End-User Efficiency <img width="20" height="20" src="../images/help.jpg" id="af7" />: <g:textField id="af7Count" name="af7Count" value="${viewBean?.af7Count}"  /></p>
<div id="helpAf7" >
    <p>Definition:
    End-User Efficiency describes the degree of consideration for human factors
    and ease of use for the user of the application measured.
    The on-line functions provided emphasize a design for user efficiency (human
    factor/user friendliness). The design includes:
    <br/>
    <li>• Navigational aids (e.g., function keys, jumps, dynamically generated menus, hyper-links)</li>
    <li>• Menus</li>
    <li>• On-line help and documents</li>
    <li>• Automated cursor movement</li>
    <li>• Scrolling</li>
    <li>• Remote printing (via on-line transmissions)</li>
    <li>• Pre-assigned function keys (e.g., clear screen, request help, clone screen)</li>
    <li>• Batch jobs submitted from on-line transactions</li>
    <li>• Drop down List box</li>
    <li>• Heavy use of reverse video, highlighting, colors, underlining, and other indicators</li>
    <li>• Hard-copy documentation of on-line transactions (e.g., screen print)</li>
    <li>• Mouse interface</li>
    <li>• Pop-up windows</li>
    <li>• Templates and/or defaults</li>
    <li>• Bilingual support (supports two languages: count as four items)</li>
    <li>• Multi-lingual support (supports more than two languages: count as six items)</li>
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>None of the above.</td></tr>
        <tr><td>1</td><td>One to three of the above.</td></tr>
        <tr><td>2</td><td>Four to five of the above.</td></tr>
        <tr><td>3</td><td>Six or more of the above, but there are no specific user requirements related to efficiency.</td></tr>
        <tr><td>4</td><td>Six or more of the above, and stated requirements for user efficiency are strong enough to require <b>design tasks</b> for human factors to be included</td></tr>
        <tr><td>5</td><td>Six or more of the above, and stated requirements for user efficiency are strong enough to require <b>use of special tools and processes</b> in order to demonstrate that the objectives have been achieved.</td></tr>

    </table>
</div>
<p >8. Online Update <img width="20" height="20" src="../images/help.jpg" id="af8" />: <g:textField id="af8Count" name="af8Count" value="${viewBean?.af8Count}"  /></p>
<div id="helpAf8" >
    <p>Definition: On-line Update describes the degree to which internal logical files are updated on-line.
    The application provides on-line update for the internal logical files.</p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>None.</td></tr>
        <tr><td>1</td><td>On-line update of one to three control files is included. Volume of updating is low and recovery is easy.</td></tr>
        <tr><td>2</td><td>On-line update of four or more control files is included. Volume of updating is low and recovery is easy.</td></tr>
        <tr><td>3</td><td>On-line update of major internal logical files is included.</td></tr>
        <tr><td>4</td><td>In addition, protection against data loss is essential and has been specially designed and programmed in the system.</td></tr>
        <tr><td>5</td><td>In addition, high volumes bring cost considerations into the recovery process. Highly automated recovery procedures with minimum human intervention are included.</td></tr>

    </table>
</div>
<p >9. Complex Processing <img width="20" height="20" src="../images/help.jpg" id="af9" />: <g:textField id="af9Count" name="af9Count" value="${viewBean?.af9Count}"  /></p>
<div id="helpAf9" >
    <p>
        Complex processing describes the degree to which processing logic
        influenced the development of the application. The following components are
        present:
        <li>• Sensitive control and/or application-specific security processing.</li>
        <li>• Extensive logical processing</li>
        <li>• Extensive mathematical processing</li>
        <li>• Much exception processing, resulting in incomplete transactions that must be processed again.</li>
        <li>• Complex processing to handle multiple input/output possibilities.</li>

    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>None of the above.</td></tr>
        <tr><td>1</td><td>Any one of the above.</td></tr>
        <tr><td>2</td><td>Any two of the above.</td></tr>
        <tr><td>3</td><td>Any three of the above.</td></tr>
        <tr><td>4</td><td>Any four of the above.</td></tr>
        <tr><td>5</td><td>Any five of the above.</td></tr>

    </table>
</div>
<p >10. Reusability <img width="20" height="20" src="../images/help.jpg" id="af10" />: <g:textField id="af10Count" name="af10Count" value="${viewBean?.af10Count}"  /></p>
<div id="helpAf10" >
    <p>Definition: Reusability describes the degree to which the application and the code in the
    application have been specifically designed, developed, and supported to be
    usable in other applications.</p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>No reusable code </td></tr>
        <tr><td>1</td><td>Reusable code is used within the application.</td></tr>
        <tr><td>2</td><td>Less than 10% of the application code developed is intended for use in more than one application.</td></tr>
        <tr><td>3</td><td>Ten percent (10%) or more of the application code developed is intended for use in more than one application.</td></tr>
        <tr><td>4</td><td>The application was specifically packaged and/or documented to ease reuse, and the application is customized at the source code level.</td></tr>
        <tr><td>5</td><td>The application was specifically packaged and/or documented to ease reuse, and the application is customized for use by means of user parameter maintenance.</td></tr>

    </table>
</div>
<p >11. Installation Ease <img width="20" height="20" src="../images/help.jpg" id="af11" />: <g:textField id="af11Count" name="af11Count" value="${viewBean?.af11Count}"  /></p>
<div id="helpAf11" >
    <p>Definition: Installation Ease describes the degree to which conversion from previous
    environments influenced the development of the application.
    Conversion and installation ease are characteristics of the application. A
    conversion and installation plan and/or conversion tools were provided and
    tested during the system test phase.</p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>No special considerations were stated by the user, <b>and no</b> special setup is required for installation.</td></tr>
        <tr><td>1</td><td>No special considerations were stated by the user, <b>but</b> special setup is required for installation</td></tr>
        <tr><td>2</td><td>Conversion and installation requirements were stated by the user, and conversion and installation guides were provided and tested. The impact of conversion on the project <b>is not</b> considered to be important.</td></tr>
        <tr><td>3</td><td>Conversion and installation requirements were stated by the user, and conversion and installation guides were provided and tested. The impact of conversion on the project <b>is</b> considered to be important.</td></tr>
        <tr><td>4</td><td>In addition to 2 above, automated conversion and installation tools were provided and tested.</td></tr>
        <tr><td>5</td><td>In addition to 3 above, automated conversion and installation tools were provided and tested.</td></tr>

    </table>
</div>
<p >12. Operational Ease <img width="20" height="20" src="../images/help.jpg" id="af12" />: <g:textField id="af12Count" name="af12Count" value="${viewBean?.af12Count}"  /></p>
<div id="helpAf12" >
    <p>Definition: Operational Ease describes the degree to which the application attends to
    operational aspects, such as start-up, back-up, and recovery processes.
    Operational ease is a characteristic of the application. The application
    minimizes the need for manual activities, such as tape mounts, paper
    handling, and direct on-location manual intervention.</p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>No special operational considerations other than the normal back-up procedures were stated by the user.</td></tr>
        <tr><td>1-4 </td><td>One, some, or all of the following items apply to the application. Select all that apply. Each item has a point value of one, except as noted otherwise.
            <li>Start-up, back-up, and recovery processes were provided, but human intervention is required.</li>
            <li>Start-up, back-up, and recovery processes were provided, but <b>no</b> human intervention is required (count as two items)</li>
            <li>The application minimizes the need for tape mounts and/or remote data access requiring human intervention.</li>
            <li>The application minimizes the need for paper handling.</li>
        </td></tr>

        <tr><td>5</td><td>The application is designed for unattended operation. Unattended operation means no human intervention is required to operate the system other than to start up or shut down the application. Automatic error recovery is a feature of the application.</td></tr>

    </table>
</div>
<p >13. Multiple Sites <img width="20" height="20" src="../images/help.jpg" id="af13" />: <g:textField id="af13Count" name="af13Count" value="${viewBean?.af13Count}"  /></p>
<div id="helpAf13" >
    <p>Definition: Installation Ease describes the degree to which conversion from previous
    environments influenced the development of the application.
    Conversion and installation ease are characteristics of the application. A
    conversion and installation plan and/or conversion tools were provided and
    tested during the system test phase.</p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>The needs of <b>only one</b> installation site were considered in the design.</td></tr>
        <tr><td>1</td><td>The needs of more than one installation site were considered in the design, and the application is designed to operate only under <b>identical</b> hardware and software environments.</td></tr>
        <tr><td>2</td><td>The needs of more than one installation site were considered in the design, and the application is designed to operate only under <b>similar</b> hardware and/or software environments.</td></tr>
        <tr><td>3</td><td>The needs of more than one installation site were considered in the design, and the application is designed to operate under <b>different</b> hardware and/or software environments</td></tr>
        <tr><td>4</td><td>Documentation and support plan are provided and tested to support the application at multiple installation sites and the application is as described by 2.</td></tr>
        <tr><td>5</td><td>Documentation and support plan are provided and tested to support the application at multiple installation sites and the application is as described by 3.</td></tr>

    </table>
</div>
<p >14. Facilitate Change <img width="20" height="20" src="../images/help.jpg" id="af14" />: <g:textField id="af14Count" name="af14Count" value="${viewBean?.af14Count}"  /></p>
<div id="helpAf14" >
    <p>Definition
    Facilitate Change describes the degree to which the application has been
    developed for easy modification of processing logic or data structure.
    The following characteristics can apply for the application:
    <br/>
    A. Flexible Query <br/>
        <li>1. Flexible query and report facility is provided that can handle <b>simple</b>
    requests. (count as 1 item)</li>
    <li>2. Flexible query and report facility is provided that can handle requests
    of <b>average</b> complexity. (count as 2 items)</li>
    <li>3. Flexible query and report facility is provided that can handle <b>complex</b>
    requests. (count as 3 items)</li>
    <br/>
    B. Business Control Data<br/>
    <li>1. Business control data is kept in tables that are maintained by the user
    with on-line interactive processes, but changes take effect only on the
    <b>next</b> business cycle. (count as 1 item)</li>
    <li>2. Business control data is kept in tables that are maintained by the user
    with on-line interactive processes, and the changes take effect
    <b>immediately</b>. (count as 2 items)</li>
    </p>
    <table>
        <th><b>Score As</b></th><th><b>Descriptions to Determine Degree of Influence</b></th>
        <tr><td>0</td><td>None of the above.</td></tr>
        <tr><td>1</td><td>A total of one item from above.</td></tr>
        <tr><td>2</td><td>A total of two item from above.</td></tr>
        <tr><td>3</td><td>A total of three item from above.</td></tr>
        <tr><td>4</td><td>A total of four item from above.</td></tr>
        <tr><td>5</td><td>A total of five item from above.</td></tr>

    </table>
</div>
<input type="button" name="save" value="save" onclick="javascript:doSaveAdjustmentFactor();"/>
<br/>

</div>
