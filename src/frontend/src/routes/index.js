import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const router = new VueRouter({
	//mode에서 history를 할 경우 서버에서 설정을 해줘야합니다. 자세한 내용은 공식문서 router history 부분을 검색 혹은 인프런 영상에 관련 내용이 있습니다.
	mode: 'history',
	routes: [
		{
			path: '/',
			//redirect: '/main',
			component: () => import('@/views/Login.vue'),
		},
		{
			path: '/login',
			component: () => import('@/views/Login.vue'),
		},
		{
			path: '/login/terms',
			component: () => import('@/components/login/Terms.vue'),
		},

		{
			path: '/signup',
			component: () => import('@/views/Signup.vue'),
		},
		{
			path: '/bill/day-bill-list',
			component: () => import('@/components/bill/popup/DayBillList.vue'),
			name: 'dayBillList',
			meta: {
				auth: true,
			},
		},
		{
			path: '*',
			component: () => import('@/views/NotFoundPage.vue'),
		},
	],
});


export default router;
