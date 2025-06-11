<template>
  <div class="content">
    <div class="text main-text">欢迎使用 {{ this.$project.projectName }}</div>
    <!-- 智能推荐区块 -->
    <div v-if="books.length > 0" class="recommend-section">
      <el-card shadow="always">
        <div slot="header" class="clearfix">
          <span>为你智能推荐</span>
        </div>
        <el-row :gutter="20">
          <el-col :span="6" v-for="book in books" :key="book.bookId">
            <el-card class="book-card" shadow="hover">
              <img :src="book.cover || defaultCover" alt="封面" style="width:100%;height:180px;object-fit:cover;">
              <div class="book-title">{{ book.title }}</div>
              <el-rate v-if="book.score" :value="book.score" disabled show-score></el-rate>
              <router-link :to="`/bookDetail/${book.bookId}`">
                <el-button type="primary" size="mini" style="margin-top:10px">查看详情</el-button>
              </router-link>
            </el-card>
          </el-col>
        </el-row>
        <el-empty v-if="books.length === 0" description="暂无推荐"></el-empty>
      </el-card>
    </div>
  </div>
</template>

<script>
import router from '@/router/router-static'
import axios from 'axios'
export default {
  data() {
    return {
      books: [],
      defaultCover: '/static/img/default-book.png'
    }
  },
  mounted() {
    this.init();
    this.loadRecommend();
  },
  methods: {
    init() {
      if (this.$storage.get('Token')) {
        this.$http({
          url: `${this.$storage.get('sessionTable')}/session`,
          method: "get"
        }).then(({ data }) => {
          if (data && data.code != 0) {
            router.push({ name: 'login' })
          }
        });
      } else {
        router.push({ name: 'login' })
      }
    },
    loadRecommend() {
      // 推荐接口，需后端返回VO含title/cover/score
      let userId = this.$storage.get('userid') || this.$storage.get('userId') || localStorage.getItem('userId')
      if (!userId) return
      axios.get(`/api/recommend/${userId}`).then(res => {
        this.books = Array.isArray(res.data) ? res.data : []
      }).catch(() => {
        this.books = []
      })
    }
  }
};
</script>

<style lang="scss" scoped>
.content {
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  min-height: 500px;
  text-align: center;
  .main-text {
    font-size: 38px;
    font-weight: bold;
    margin-top: 12%;
    margin-bottom: 20px;
  }
  .text {
    font-size: 24px;
    font-weight: bold;
    color: #333;
  }
  .recommend-section {
    width: 90%;
    margin-top: 30px;
    .book-title {
      font-weight: bold;
      margin: 10px 0 5px 0;
      font-size: 16px;
      text-align: center;
    }
    .book-card {
      min-height: 280px;
    }
  }
}
</style>