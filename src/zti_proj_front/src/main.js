import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from "./router";
import VueGoodTablePlugin from 'vue-good-table-next';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import 'vue-good-table-next/dist/vue-good-table-next.css'

const app = createApp(App);
app.use(VueGoodTablePlugin);
app.use(router)
app.mount('#app');
