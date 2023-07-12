import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import yinlexinxiList from '../pages/yinlexinxi/list'
import yinlexinxiDetail from '../pages/yinlexinxi/detail'
import yinlexinxiAdd from '../pages/yinlexinxi/add'
import yinleleixingList from '../pages/yinleleixing/list'
import yinleleixingDetail from '../pages/yinleleixing/detail'
import yinleleixingAdd from '../pages/yinleleixing/add'
import geshouList from '../pages/geshou/list'
import geshouDetail from '../pages/geshou/detail'
import geshouAdd from '../pages/geshou/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'yinlexinxi',
					component: yinlexinxiList
				},
				{
					path: 'yinlexinxiDetail',
					component: yinlexinxiDetail
				},
				{
					path: 'yinlexinxiAdd',
					component: yinlexinxiAdd
				},
				{
					path: 'yinleleixing',
					component: yinleleixingList
				},
				{
					path: 'yinleleixingDetail',
					component: yinleleixingDetail
				},
				{
					path: 'yinleleixingAdd',
					component: yinleleixingAdd
				},
				{
					path: 'geshou',
					component: geshouList
				},
				{
					path: 'geshouDetail',
					component: geshouDetail
				},
				{
					path: 'geshouAdd',
					component: geshouAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
