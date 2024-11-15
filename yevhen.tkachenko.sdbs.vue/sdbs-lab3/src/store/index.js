import { createStore } from 'vuex'

export default createStore({
  state: {
    films:[],
    actors:[],
    add:null,
    allActors:[],
    actorsParticipate:[],
    film:{}
  },
  getters: {
    getFilms(state){
      return state.films
    },
    getActors(state){
      return state.actors
    },
    getAllActors(state){
      return state.allActors
    },
    getActorsParticipate(state){
      return state.actorsParticipate
    },
    getFilm(state){
      return state.film
    },
  },
  mutations: {
    setFilms(state,payload){
      state.films = payload
    },
    setActors(state,payload){
      state.actors = payload
    },
    setAdd(state,payload){
      state.add = payload
    },
    setAllActors(state,payload){
      state.allActors = payload
    },
    setActorsParticipate(state,payload){
      state.actorsParticipate = payload
    },
    setFilm(state,payload){
      state.film = payload
    },
  },
  actions: {
    async getFilms({ commit }) {
      const response = await fetch(`http://127.0.0.1:8080/films`, {
              method: 'GET',
            });
            let res = await response.json()
            commit('setFilms',res)
    },
    async getActors({ commit },payload) {
      const response = await fetch(`http://127.0.0.1:8080/actors?filmId=${payload.filmId}`, {
              method: 'GET',
            });
            let res = await response.json()
            commit('setActors',res)
    },
    async getAllActors({ commit }) {
      const response = await fetch(`http://127.0.0.1:8080/actors`, {
              method: 'GET',
            });
            let res = await response.json()
            commit('setAllActors',res)
    },
    async addNew({commit},payload) {

      const response = await fetch(`http://127.0.0.1:8080/films`, {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify({
                actorId:payload.actorId,
                feeNum:payload.feeNum,
                filmName:payload.name,
                filmingPercentage:payload.percentage,
                genre:payload.genre,
                duration:payload.duration,
                rating:payload.rating,
                budgetNum:payload.budget,
                boxOfficeNum:payload.box_office,
                filmDate:payload.date,
              }),
            });
            let res = await response.json()
            if(res.error){
              alert(res.error)
            }else{
              alert("Film added succesfully")
              commit('setAdd',1)
            }
    },
    async getActorsParticipate({ commit }) {
      const response = await fetch(`http://127.0.0.1:8080/actors-participate`, {
              method: 'GET',
            });
            let res = await response.json()
            commit('setActorsParticipate',res)
    },
    async getFilm({ commit },payload) {
      const response = await fetch(`http://127.0.0.1:8080/films?year=${payload.year}`, {
              method: 'GET',
            });
            let res = await response.json()
            commit('setFilm',res)
    },
  },
  modules: {
  }
})
