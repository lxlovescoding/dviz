import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'  //使用国际化配置,主要针对日期组件
import ECharts from 'vue-echarts'
import "echarts"

createApp(App)
    .use(router)
    .use(ElementPlus, {
        locale: zhCn,
    })
    .component('Echarts', ECharts)
    .mount('#app')
