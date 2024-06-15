<template>
<!--  <el-row>-->
<!--    <el-col :span="1"/>-->
<!--    <el-col :span="10">-->
<!--&lt;!&ndash;      <AirlineDate/>&ndash;&gt;-->
<!--    </el-col>-->
<!--  </el-row>-->
  <el-row>
    <el-col :span="1"/>
    <el-col :span="10">
      <v-chart  theme=" " :option="chartOptions" autoresize
               :init-options="{ width: 1200, height: 800 }" />
    </el-col>
  </el-row>
</template>

<script>
import VChart from 'vue-echarts';
import axios from 'axios';
import { ref } from 'vue';
// import AirlineDate from "@/components/AirlineDate";

export default {
  components: {
    VChart,
    // AirlineDate
  },
  setup() {
    let chartData = ref([])
    let title = ref({
      text: '航班数量',
      left: 'center',
      subtext: ''
    })
    axios.get("http://localhost:8888/airline_count_data").then(
        function (response) {
          for (let i in response.data) {
            let e = response.data[i];
            // 只展示前20
            if (i < 20) {
              chartData.value.push(e);
            }
          }
          title.value.subtext = `共${response.data.length}条航线`
        }
    )
    let chartOptions = {
      title: title.value,
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '航班数量',
          type: 'pie',
          radius: '50%',
          data: chartData.value,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };

    return {
      chartOptions,
    }
  }
}
</script>
