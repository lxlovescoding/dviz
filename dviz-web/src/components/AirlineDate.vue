<template>
  <el-row>
    <el-col :span="3"/>
    <el-col :span="10">
      <v-chart :option="option" autoresize
               :init-options="{ width: 1200, height: 400 }" />
    </el-col>
  </el-row>
</template>

<script>
import VChart from 'vue-echarts';
import axios from 'axios';
import { ref } from 'vue';
import * as echarts from "echarts";

export default {
  components: {
    VChart
  },
  setup() {

    let dateData = ref([]);
    axios.get("http://localhost:8888/airline_date_data").then(
        function (response) {
          for (let e of response.data) {
            dateData.value.push([
              echarts.time.format(echarts.time.parse(e.date), '{yyyy}-{MM}-{dd}', false),
              Number(e.count)
            ]);
          }
        }
    )

    let option = {
      title: {
        top: 30,
        left: 'center',
        text: '每日航班数热力表'
      },
      tooltip: {},
      visualMap: {
        min: 0,
        max: 25000,
        type: 'piecewise',
        orient: 'horizontal',
        left: 'center',
        top: 65
      },
      calendar: {
        top: 120,
        left: 30,
        right: 30,
        cellSize: ['auto', 15],
        range: '2007',
        itemStyle: {
          borderWidth: 0.5
        },
        yearLabel: { show: true }
      },
      series: {
        type: 'heatmap',
        coordinateSystem: 'calendar',
        data: dateData.value
      }
    };

    return {
      option
    }
  }
}
</script>
