<template>
  <el-row>
      <v-chart theme=" " :option="option0" autoresize
               :init-options="{ width: 1500, height: 300 }" />
  </el-row>
  <el-row>
    <v-chart theme=" " :option="option1" autoresize
             :init-options="{ width: 1500, height: 300 }" />
  </el-row>
  <el-row>
    <v-chart theme=" " :option="option2" autoresize
             :init-options="{ width: 1500, height: 300 }" />
  </el-row>
</template>

<script>
import VChart from 'vue-echarts';
import axios from 'axios';
import { ref } from 'vue';

export default {
  components: {
    VChart
  },
  setup() {

    let s0 = ref([
      {
        data: null,
        type: 'line',
      }
    ]);
    let s1 = ref([
      {
        data: null,
        type: 'line',
      }
    ]);
    let s2 = ref(
      {
        name: '起飞',
        data: null,
        type: 'line',
        // stack: 'Total',
      }
    );
    let s3 = ref(
      {
        name: '到达',
        data: null,
        type: 'line',
        // stack: 'Total',
      }
    );
    let s4 = ref([]);
    axios.get("http://localhost:8888/airline_time_data").then(
        function (response) {
          let d0 = Array(24);
          let d1 = Array(24);
          for (let e of response.data) {
            if (e.type == 0) {
              d0[e.index] = e.count;
            } else if (e.type == 1) {
              d1[e.index] = e.count;
            }
          }
          s0.value[0].data = d0;
          s1.value[0].data = d1;
          s2.value.data = d0;
          s3.value.data = d1;
          s4.value.push(s2.value);
          s4.value.push(s3.value);
        }
    )

    let option0 = {
      title: {
        text: '航班起飞时间分布', // 标题文本
        left: 'center', // 标题位置
        top: 20, // 标题距离顶部距离
        textStyle: {
          fontWeight: 'bold', // 标题字体加粗
          fontSize: 16 // 标题字体大小
        }
      },
      xAxis: {
        type: 'category',
        data: ['00:00~01:00', '01:00~02:00', '02:00~03:00', '03:00~04:00', '04:00~05:00', '05:00~06:00', '06:00~07:00', '07:00~08:00', '08:00~09:00', '09:00~10:00', '10:00~11:00', '11:00~12:00', '12:00~13:00', '13:00~14:00', '14:00~15:00', '15:00~16:00', '16:00~17:00', '17:00~18:00', '18:00~19:00', '19:00~20:00', '20:00~21:00', '21:00~22:00', '22:00~23:00', '23:00~24:00']
      },
      yAxis: {
        type: 'value'
      },
      series: s0.value
    };

    let option1 = {
      title: {
        text: '航班到达时间分布', // 标题文本
        left: 'center', // 标题位置
        top: 20, // 标题距离顶部距离
        textStyle: {
          fontWeight: 'bold', // 标题字体加粗
          fontSize: 16 // 标题字体大小
        }
      },
      xAxis: {
        type: 'category',
        data: ['00:00~01:00', '01:00~02:00', '02:00~03:00', '03:00~04:00', '04:00~05:00', '05:00~06:00', '06:00~07:00', '07:00~08:00', '08:00~09:00', '09:00~10:00', '10:00~11:00', '11:00~12:00', '12:00~13:00', '13:00~14:00', '14:00~15:00', '15:00~16:00', '16:00~17:00', '17:00~18:00', '18:00~19:00', '19:00~20:00', '20:00~21:00', '21:00~22:00', '22:00~23:00', '23:00~24:00']
      },
      yAxis: {
        type: 'value'
      },
      series: s1.value
    };

    let option2 = {
      title: {
        text: '起飞到达时间对比'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: ['起飞', '到达']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['00:00~01:00', '01:00~02:00', '02:00~03:00', '03:00~04:00', '04:00~05:00', '05:00~06:00', '06:00~07:00', '07:00~08:00', '08:00~09:00', '09:00~10:00', '10:00~11:00', '11:00~12:00', '12:00~13:00', '13:00~14:00', '14:00~15:00', '15:00~16:00', '16:00~17:00', '17:00~18:00', '18:00~19:00', '19:00~20:00', '20:00~21:00', '21:00~22:00', '22:00~23:00', '23:00~24:00']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        s2.value,
          s3.value
      ]
    };

    return {
      option0,
      option1,
      option2
    }
  }
}
</script>
