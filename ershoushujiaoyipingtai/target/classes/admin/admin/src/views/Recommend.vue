<template>
  <div class="recommend-page">
    <h2>为你推荐的图书</h2>
    <el-row :gutter="20">
      <el-col :span="6" v-for="book in books" :key="book.bookId">
        <el-card>
          <img :src="book.cover || defaultCover" style="width:100%;height:180px;object-fit:cover;" alt="封面" />
          <div class="book-title">{{ book.title }}</div>
          <el-rate v-if="book.score" :value="book.score" disabled show-score></el-rate>
          <router-link :to="`/book/${book.bookId}`">
            <el-button type="primary" size="mini" style="margin-top:10px">查看详情</el-button>
          </router-link>
        </el-card>
      </el-col>
    </el-row>
    <el-empty v-if="books.length === 0" description="暂无推荐"></el-empty>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'
const books = ref([])
const userId = Number(localStorage.getItem('userId') || 1)
const defaultCover = '/assets/default-book.png'

onMounted(async () => {
  try {
    // 推荐接口建议返回BookRecommendVO（含title、cover）
    const { data } = await axios.get(`/api/recommend/${userId}`)
    books.value = Array.isArray(data) ? data : []
  } catch (e) {
    ElMessage.error('获取推荐失败')
    books.value = []
  }
})
</script>

<style scoped>
.recommend-page { padding: 24px; }
.book-title { font-weight: bold; margin: 10px 0 5px 0; font-size: 16px; text-align: center; }
</style>