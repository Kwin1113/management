var nn = new Vue({
    el: '#app',
    data() {
        return{
            msg: 'hello',
            infos: null,
            queryDate: null
        }
    },
    mounted() {
        // axios
        // .get('http://localhost:8080/test?id=2')
        // .then(response => (this.infos = response.data))
        // .catch(function(error){
        //     console.log(error);
        // });
    },
    watch: {
        queryDate(newDate, oldDate){
            alert(newDate)
            this.queryDate = newDate
            alert("11")
            console.log('old date is ' + oldDate)
            console.log('new date is ' + newDate)
        }
    }
})

function aa(id) {
    axios
    .get('http://localhost:8080/test?id=' + id)
    .then(response => (nn.infos = response.data))
    .catch(function(error){
        console.log(error);
    });
}

$(function () {
    $(".datepicker").datepicker({
        language: "zh-CN",
        autoclose: true,//选中之后自动隐藏日期选择框
        clearBtn: true,//清除按钮
        todayBtn: true,//今日按钮
        format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
    }).on('changeDate', gotoDate);
});

function gotoDate(){
    // alert($("#queryDate").data("datepicker").getDate())
    var date = $('#queryDate').datepicker('getDate')
    // debugger
    // alert($('#queryDate').datepicker('getDate'))
    // nn.queryDate=$('#queryDate').datepicker('getDate')
    axios
    .post('http://localhost:8080/test?id=' + date)
    .then(response => (nn.infos = response.data))
    .catch(function(error){
        console.log(error);
    });
}