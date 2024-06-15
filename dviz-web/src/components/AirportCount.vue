<template>
  <el-row>
    <el-col :span="1"/>
    <el-col :span="10">
      <v-chart theme=" " ref="myChart" :option="chartOptions" autoresize
               :init-options="{width: 1200, height: 800 }" />
    </el-col>
    <el-col :span="4"/>
<!--    <el-col :span="10">-->
<!--      <el-table :data="tableData" height="800" style="width: 100%"-->
<!--                :default-sort="{ prop: 'value', order: 'descending' }"-->
<!--      >-->
<!--        <el-table-column type="index" :index="indexMethod" width="120"/>-->
<!--        <el-table-column prop="name" label="机场" width="180" />-->
<!--        <el-table-column prop="value" sortable label="数量" width="180" />-->
<!--      </el-table>-->
<!--    </el-col>-->
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
    let chartData = ref([])
    let tableData = ref([])
    let title = ref({
      text: '航班机场数量',
      left: 'center',
      subtext: ''
    })
    axios.get("http://localhost:8888/airport_count_data").then(
      function (response) {
        let otherSum = 0;
        for (let i in response.data) {
          let e = response.data[i];
          // 只展示前20
          if (i < 20) {
            chartData.value.push(e);
          } else {
            otherSum += response.data[i].value;
          }
          tableData.value.push(e);
        }
        chartData.value.push({
          name: "其他",
          value: otherSum
        })
        title.value.subtext = `共${tableData.value.length}个机场`
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
          name: '机场数量',
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

    const indexMethod = (index) => {
      return index + 1
    }

    return {
      indexMethod,
      tableData,
      chartOptions
    }
  }
}
</script>
