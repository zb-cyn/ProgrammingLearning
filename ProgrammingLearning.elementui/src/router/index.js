import store from "@/store";
import { getToken } from "@/utils/cache";
import AdminLayout from "@/views/Admin/Layout/index.vue";
import FrontLayout from "@/views/Front/Layout/index.vue";
import NProgress from "nprogress"; // progress bar
import "nprogress/nprogress.css"; // progress bar style
import Vue from "vue";
import VueRouter from "vue-router";
NProgress.inc(0.2);
NProgress.configure({
  easing: "ease",
  speed: 1000,
  showSpinner: false,
  trickle: false,
});
Vue.use(VueRouter);
const routes = [
  {
    path: "/",
    redirect: "/Front",
  },
  {
    path: "/Login",
    component: () => import("@/views/Login.vue"),
  },
  {
    path: "/Register",
    component: () => import("@/views/Register.vue"),
  },
  {
    path: "/ForgetPassword",
    component: () => import("@/views/ForgetPassword.vue"),
  },
];
//前台的路由
export const frontRouters = [
  {
    path: "/Front",
    redirect: "/Front/Home",
    component: FrontLayout,
    meta: {
      title: "前台",
      isAdmin: false,
    },
    children: [
      {
        path: "/Front/Home",
        meta: {
          isAdmin: false,
        },
        component: () => import("@/views/Front/Home"),
      },
      {
        path: "/Front/UserPerson",
        meta: {
          title: "我的个人信息",
          isAdmin: false,
        },
        component: () => import("@/views/Front/UserPerson"),
      },
      {
        path: "/Front/PasswordEdit",
        meta: {
          title: "修改密码",
          isAdmin: false,
        },
        component: () => import("@/views/Front/PasswordEdit"),
      },
      {
        path: "/Front/AskedQuestionsList",
        meta: {
          title: "常见问题",
          isAdmin: false,
        },
        component: () => import("@/views/Front/AskedQuestionsList"),
      },
      {
        path: "/Front/AskedQuestionsDetail",
        meta: {
          title: "常见问题详情",
          isAdmin: false,
        },
        component: () => import("@/views/Front/AskedQuestionsDetail"),
      },
      {
        path: "/Front/QuestionnaireList",
        meta: {
          title: "试卷测试",
          isAdmin: false,
        },
        component: () => import("@/views/Front/QuestionnaireList"),
      },
      {
        path: "/Front/QuestionnaireAnswer",
        meta: {
          title: "试卷测试试题",
          isAdmin: false,
        },
        component: () => import("@/views/Front/QuestionnaireAnswer"),
      },
      {
        path: "/Front/UserAnswerList",
        meta: {
          title: "试卷测试结果",
          isAdmin: false,
        },
        component: () => import("@/views/Front/UserAnswerList"),
      },

      {
        path: "/Front/SysNoticeDetail",
        meta: {
          title: "系统通知详情",
          isAdmin: false,
        },
        component: () => import("@/views/Front/SysNoticeDetail"),
      },
      {
        path: "/Front/SysNoticeList",
        meta: {
          title: "系统通知",
          isAdmin: false,
        },
        component: () => import("@/views/Front/SysNoticeList"),
      },
      {
        path: "/Front/TopicSearch",
        meta: {
          title: "话题搜索",
          isAdmin: false,
        },
        component: () => import("@/views/Front/TopicSearch"),
      },
      {
        path: "/Front/TopicList",
        meta: {
          title: "话题交流",
          isAdmin: false,
        },
        component: () => import("@/views/Front/TopicList"),
      },
      {
        path: "/Front/MyTopicCollect",
        meta: {
          title: "话题收藏",
          isAdmin: false,
        },
        component: () => import("@/views/Front/MyTopicCollect"),
      },
      {
        path: "/Front/MyTopicCollect",
        meta: {
          title: "我的话题收藏",
          isAdmin: false,
        },
        component: () => import("@/views/Front/MyTopicCollect"),
      },
      {
        path: "/Front/TopicMy",
        meta: {
          title: "我的话题",
          isAdmin: false,
        },
        component: () => import("@/views/Front/TopicMy"),
      },
      {
        path: "/Front/TopicDetail",
        meta: {
          title: "话题详情",
          isAdmin: false,
        },
        component: () => import("@/views/Front/TopicDetail"),
      },
      {
        path: "/Front/TaHome",
        meta: {
          title: "作者首页",
          isAdmin: false,
        },
        component: () => import("@/views/Front/TaHome"),
      },
      {
        path: "/Front/CouseDetail",
        meta: {
          title: "课程详情",
          isAdmin: false,
        },
        component: () => import("@/views/Front/CouseDetail"),
      },
      {
        path: "/Front/PlayVideo",
        meta: {
          title: "视频播放",
          isAdmin: false,
        },
        component: () => import("@/views/Front/PlayVideo"),
      },
      {
        path: "/Front/MyCouseStudyScheduleList",
        meta: {
          title: "我的学习进度",
          isAdmin: false,
        },
        component: () => import("@/views/Front/MyCouseStudyScheduleList"),
      },
      {
        path: "/Front/CouseList",
        meta: {
          title: "课程列表",
          isAdmin: false,
        },
        component: () => import("@/views/Front/CouseList"),
      },
      {
        path: "/Front/MyCouseOrder",
        meta: {
          title: "我的课程",
          isAdmin: false,
        },
        component: () => import("@/views/Front/MyCouseOrder"),
      },
      {
        path: "/Front/UserCenter",
        meta: {
          title: "用户中心",
          isAdmin: false,
        },
        component: () => import("@/views/Front/UserCenter"),
      },
      {
        path: "/Front/ToAppoint",
        meta: {
          title: "课程预约",
          isAdmin: false,
        },
        component: () => import("@/views/Front/ToAppoint"),
      },
    ],
  },
];

export const adminRouters = [
  {
    path: "/Admin",
    redirect: "/Admin/Home",
    component: AdminLayout,
    meta: {
      title: "控制台",
      isAdmin: true,
    },
    children: [
      {
        path: "/Admin/Home",
        meta: {
          isAdmin: true,
        },
        component: () => import("@/views/Admin/Home"),
      },
      {
        path: "/Admin/UserList",
        meta: {
          title: "用户信息",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/UserList"),
      },
      {
        path: "/Admin/UserPerson",
        meta: {
          title: "个人信息",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/UserPerson"),
      },
      {
        path: "/Admin/PasswordEdit",
        meta: {
          title: "修改密码",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/PasswordEdit"),
      },
      {
        path: "/Admin/AskedQuestionsList",
        meta: {
          title: "常见问题手册",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/AskedQuestionsList"),
      },
      {
        path: "/Admin/JudgmentQuestionList",
        meta: {
          title: "判断题型",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/JudgmentQuestionList"),
      },

      {
        path: "/Admin/MultipleChoiceQuestionList",
        meta: {
          title: "多选题",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/MultipleChoiceQuestionList"),
      },

      {
        path: "/Admin/QuestionnaireList",
        meta: {
          title: "试卷",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/QuestionnaireList"),
      },
      {
        path: "/Admin/QuestionOptionsDataCollect",
        meta: {
          title: "试卷选项占比",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/QuestionOptionsDataCollect"),
      },

      {
        path: "/Admin/QuestionnaireDetList",
        meta: {
          title: "试卷明细",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/QuestionnaireDetList"),
      },

      {
        path: "/Admin/SingleChoiseQuestionList",
        meta: {
          title: "单选题",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/SingleChoiseQuestionList"),
      },

      {
        path: "/Admin/UserAnswerList",
        meta: {
          title: "用户提交答题",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/UserAnswerList"),
      },

      {
        path: "/Admin/UserAnswerDetList",
        meta: {
          title: "用户提交答题明细",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/UserAnswerDetList"),
      },

      {
        path: "/Admin/QuestionnaireDet",
        meta: {
          title: "试卷明细",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/QuestionnaireDet"),
      },

      {
        path: "/Admin/UserAnswerResult",
        meta: {
          title: "试卷答题记录",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/UserAnswerResult"),
      },
      {
        path: "/Admin/SysLabelList",
        meta: {
          title: "系统标签",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/SysLabelList"),
      },

      {
        path: "/Admin/SubjectiveQuestionList",
        meta: {
          title: "主观题",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/SubjectiveQuestionList"),
      },

      {
        path: "/Admin/UserAnswerLogList",
        meta: {
          title: "考试日志",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/UserAnswerLogList"),
      },
      {
        path: "/Admin/CreateOrEditQuestionnaireDet",
        meta: {
          title: "试卷组题",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/CreateOrEditQuestionnaireDet"),
      },

      {
        path: "/Admin/TestList",
        meta: {
          title: "测试",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/TestList"),
      },
      {
        path: "/Admin/SysNoticeList",
        meta: {
          title: "系统通知",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/SysNoticeList"),
      },
      {
        path: "/Admin/TopicList",
        meta: {
          title: "话题",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/TopicList"),
      },
      {
        path: "/Admin/TopicTypeList",
        meta: {
          title: "话题类型",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/TopicTypeList"),
      },
      {
        path: "/Admin/TopicAnalyseEchart",
        meta: {
          title: "话题统计",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/TopicAnalyseEchart"),
      },
      {
        path: "/Admin/TopicSynthesizeAnalyse",
        meta: {
          title: "话题综合分析",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/TopicSynthesizeAnalyse"),
      },
      {
        path: "/Admin/CouseOrderList",
        meta: {
          title: "课程订单",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/CouseOrderList"),
      },
      {
        path: "/Admin/CouseList",
        meta: {
          title: "课程",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/CouseList"),
      },

      {
        path: "/Admin/CouseClockList",
        meta: {
          title: "课程打卡",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/CouseClockList"),
      },
      {
        path: "/Admin/CouseTypeList",
        meta: {
          title: "课程分类",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/CouseTypeList"),
      },
      {
        path: "/Admin/CouseStepList",
        meta: {
          title: "课程章节",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/CouseStepList"),
      },

      {
        path: "/Admin/CouseStudyScheduleList",
        meta: {
          title: "课程学习进度",
          isAdmin: true,
        },
        component: () => import("@/views/Admin/CouseStudyScheduleList"),
      },
    ],
  },
];

const router = new VueRouter({
  routes: [...routes, ...frontRouters, ...adminRouters], // (缩写) 相当于 routes: routes
});

// 编写导航路由
router.beforeEach(async (to, from, next) => {
  NProgress.start();
  const hasToken = getToken();

  if (hasToken) {
    if (store.getters.HasUserInfo == false) {
      await store.dispatch("GetInfo");
      if (store.getters.UserId == 0 || store.getters.UserId == null) {
        store.dispatch("Logout");
      }
    }
    //如果去往的页面是后台
    if (to.meta && to.meta.isAdmin) {
      if (store.getters.RoleType == "管理员") {
        next();
      } else {
        next({ path: "/" });
      }
    } else {
      if (store.getters.RoleType == "管理员") {
        next({ path: "/Admin" });
      } else {
        next();
      }
    }
  } else {
    if (to.meta && to.meta.isAdmin) {
      next({ path: "/Login" });
    } else {
      next();
    }
  }
  NProgress.done();
});
router.afterEach(() => {
  // finish progress bar
  window.scrollTo({ top: 0 });
  NProgress.done();
});

export default router;
