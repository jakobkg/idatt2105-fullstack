import { createRouter, createWebHistory } from "vue-router";
import CalculatorView from "../views/CalculatorView.vue";
import FeedbackView from "../views/FeedbackView.vue";
import LoginView from "../views/LoginView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "calculator",
      component: CalculatorView,
    },
    {
      path: "/feedback",
      name: "feedback",
      component: FeedbackView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView
    }
  ],
});

export default router;
