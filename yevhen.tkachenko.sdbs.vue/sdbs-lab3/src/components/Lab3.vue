<template>
    <div class="wrapper">
      <div class="inner">
        <label for="category-select">Виберіть категорію:</label>
            <select v-model="selectedFilmId" id="category-select">
            <option v-for="film in this.$store.getters.getFilms" :key="film.filmId" :value="film.filmId">
                {{ film.filmName }}
            </option>
            </select>


        <div v-for = "c in this.$store.getters.getActors" :key="c.actorId">
          <p>{{ c.actorFullName }}</p>
        </div>

      <h1 class="head">Зняти актора в новому фільмі</h1>
      <div class="addblock">
            <label for="category-select">Виберіть актора</label>
            <select v-model="selectedActorId" id="category-select">
            <option v-for="actor in this.$store.getters.getAllActors" :key="actor.actorId" :value="actor.actorId">
                {{ actor.actorFullName }}
            </option>
            </select>
            <div class="add-block">
                <p>Гонорар актора</p>
                <input type="number" v-model="fee_num" >
            </div>
            <div class="add-block">
                <p>Назва фільму</p>
                <input type="text" v-model="name" >
            </div>
            <div class="add-block">
                <p>Скільки відсотків знаято</p>
                <input type="number" v-model="percentage" >
            </div>
            <div class="add-block">
                <p>Жанр</p>
                <input type="text" v-model="genre" >
            </div>
            <div class="add-block">
                <p>Тривалість</p>
                <input type="text" v-model="duration" >
            </div>
            <div class="add-block">
                <p>Рейтинг</p>
                <input type="number" v-model="rating" >
            </div>
            <div class="add-block">
                <p>Бюджет</p>
                <input type="number" v-model="budget" >
            </div>
            <div class="add-block">
                <p>Збори</p>
                <input type="number" v-model="box_office" >
            </div>
            <div class="add-block">
                <p>Дата</p>
                <input type="date" v-model="date" >
            </div>
       </div>
       <button @click = "add">Додати</button>
      </div>
      <div class="inner">
        <h1 class="head">Загальні гонорари акторів</h1>
       <div v-for = "c in this.$store.getters.getActorsParticipate" :key="c?.actor?.actorFullName">
          <p>{{ c?.actor?.actorFullName }} заробив {{ c?.feeNum }}</p>
        </div>

        <h1 class="head">Найприбутковіший фільм року</h1>
        <input type="number" v-model="year">
        <button @click="getByYear">Отримати</button>
        <p v-if="film?.filmName">Найуспішніший фільм  {{ year }} року - {{ film?.filmName }} {{ this.$store.getters.getFilm.filmDate }} зі зборами {{  this.$store.getters.getFilm.boxOfficeNum }} $</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Lab3C',
    data() {
      return {
        selectedFilmId: 1,
        fee_num:0,
        name:"",
        percentage:0,
        genre:"",
        duration:"00:00:00",
        rating:0,
        budget:0,
        box_office:0,
        date:"2023-01-01",
        selectedActorId:1,
        year:2024
      };
    },
    mounted(){
      this.$store.dispatch('getFilms')
      this.$store.dispatch('getAllActors')
      this.$store.dispatch('getActorsParticipate')
      this.$store.dispatch('getActors',{
          filmId:this.selectedFilmId
        })
    },
    watch:{
        selectedFilmId(){
        this.$store.dispatch('getActors',{
          filmId:this.selectedFilmId
        })
      }
    },
    methods: {
        async add() {
            await this.$store.dispatch('addNew',{
                actorId:this.selectedActorId,
                feeNum:this.fee_num,
                name:this.name,
                percentage:this.percentage,
                genre:this.genre,
                duration:this.duration,
                rating:this.rating,
                budget:this.budget,
                box_office:this.box_office,
                date:this.date,
            })
            await this.$store.dispatch('getFilms')
            await this.$store.dispatch('getActorsParticipate')
        },
        getByYear(){
          this.$store.dispatch('getFilm',{year:this.year})
        }
    },
    computed:{
      film(){
        console.log(this.$store.getters.getFilm)
        return this.$store.getters.getFilm
      }
    }
  }
  </script>
  
  <style>
  .head{
    margin-top: 75px;
  }
  .wrapper{
    display: flex;
    justify-content: space-between;
  }
  .inner{
    width: 45%;
  }
</style>