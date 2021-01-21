import { createRouter, createWebHistory } from 'vue-router';
// import { h } from 'vue';
import Home from '../views/Home.vue';
import Bbs from '../views/Bbs.vue';
import BbsNew from '../views/BbsNew.vue';
import BbsDetail from '../views/BbsDetail.vue';
import Layout from '../views/layout/Layout.vue';
import LocaleComponent from '../components/common/LocaleComponent.vue';

const routes = [
  {
    path: '/',
    // name: 'Home',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Home',
        component: Home,
      },
      {
        path: 'bbs', // 게시판
        component: LocaleComponent,
        children: [
          {
            path: '', // 게시판 리스트
            name: 'Bbs',
            component: Bbs,
          },
          {
            path: 'new', // 게시글 작성
            name: 'BbsNew',
            component: BbsNew,
          },
          {
            path: ':bbsId', // 게시글 작성
            name: 'BbsDetail',
            component: BbsDetail,
          },
        ],
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
