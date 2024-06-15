<template>
  <el-row>
    <el-col :span="20">
      <el-table :data="tableData" height="800" style="width: 100%"
                :default-sort="{ prop: 'distance', order: 'descending' }"
      >
        <el-table-column type="index" :index="indexMethod" width="120"/>
        <el-table-column prop="origin" label="起点" width="180" />
        <el-table-column prop="dest" label="终点" width="180" />
        <el-table-column prop="distance" sortable label="距离(km)" width="180" />
        <el-table-column prop="count" sortable label="航班数量" width="180" />
      </el-table>
    </el-col>
  </el-row>


</template>

<script>
import axios from 'axios';
import { ref } from 'vue';

export default {
  setup() {
    let tableData = ref([])
    let title = ref({
      text: '航班机场数量',
      left: 'center',
      subtext: ''
    })
    axios.get("http://localhost:8888/airline_count_data").then(
        function (response) {
          for (let i in response.data) {
            let e = response.data[i];
            tableData.value.push({
              origin: e.origin,
              dest: e.dest,
              count: e.value,
              distance: e.distance
            });
          }
          title.value.subtext = `共${tableData.value.length}个机场`
        }
    )
    const indexMethod = (index) => {
      return index + 1
    }
    return {
      indexMethod,
      tableData
    }
  }
}
</script>
