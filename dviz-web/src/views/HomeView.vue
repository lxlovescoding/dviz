<template>
  <el-row class="tac" style="height: 100%">
    <el-col :span="4" style="font-family: 'Consolas',se
    rif">
      <h1 style="margin-left: 20px;">航班可视化</h1>
      <el-menu
          default-active="1"
      >
        <el-menu-item index="1" @click="changePage(1)">
          <el-icon><location /></el-icon>
          <h2>航班机场</h2>
        </el-menu-item>
        <el-menu-item index="2" @click="changePage(2)">
          <el-icon><Position /></el-icon>
          <h2>航线</h2>
        </el-menu-item>
        <el-menu-item index="3" @click="changePage(3)">
          <el-icon><Timer /></el-icon>
          <h2>航线时刻</h2>
        </el-menu-item>
<!--        <el-menu-item index="4" @click="changePage(4)">-->
<!--          <el-icon><document /></el-icon>-->
<!--          <h2>Airline Data</h2>-->
<!--        </el-menu-item>-->
      </el-menu>
    </el-col>
    <el-col :span="20">
      <div v-if="page === 1" style="padding: 100px;">
        <AirportCount />
      </div>
      <div v-if="page === 2" >
        <AirlineCount/>
      </div>
      <div v-if="page === 3" >
        <AirlineTime/>
      </div>
      <div v-if="page === 4" style="padding: 100px;">
        <AirlineData/>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import {
  // Document,
  Timer,
  Location,
  Position
} from '@element-plus/icons-vue';
import AirportCount from "@/components/AirportCount";
import AirlineCount from "@/components/AirlineCount";
import { ref } from  'vue';
import AirlineData from "@/components/AirlineData";
import AirlineTime from "@/components/AirlineTime";


export default {
  name: 'HomeView',
  components: {
    AirlineData,
    AirportCount,
    // Document,
    Timer,
    Location,
    Position,
    AirlineCount,
    AirlineTime,
  },
  setup() {
    let page = ref(1);

    const changePage = (pageNum) => {
      page.value = Number(pageNum)
    }

    return {
      page,
      changePage,
    }
  }
}
</script>

<style scoped>

</style>
