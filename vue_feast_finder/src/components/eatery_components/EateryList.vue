<template>

  <div v-if="$store.state.showRestaurants" class="eatery-grid-container">
    <!-- for now this can be helpful for debugging api calls may also be part
    of an added feature later -->
    <!-- <h1>{{ this.search }}</h1> -->

    <!-- Left Chevron Icon -->
    <button v-if="showBack && !$store.state.moreDetailsView" v-on:click="nextPage(false)">
      <i class="fas fa-chevron-left"></i>
    </button>
    <EateryCard :restaurants="restaurants" />

    <!-- Right Chevron Icon -->
    <button v-if="showForward && !$store.state.moreDetailsView" v-on:click="nextPage(true)">
      <i class="fas fa-chevron-right"></i>
    </button>
  </div>

</template>

<script>
import EateryCard from "./EateryCard.vue";
import RestaurantService from "../../services/RestaurantService";

export default {
  data() {
    return {
      restaurants: [],
      currentResponse: [],
      start: 0,
      end: 3,
      search: '',
      showForward: true,
      showBack: false,
    };
  },
  components: {
    EateryCard,
  },
  methods: {
    findEatery() {
      this.isLoading = true;
      this.restaurants = [];
      this.currentResponse = [];
      this.start = 0;
      this.end = 3;

      this.search = ``

      let location = this.$store.state.currentSearch;
      let term = this.$store.state.term;
      let category = this.$store.state.category;
      if (location === ``) {
        this.search = `latitude=${this.$store.state.latitude}&longitude=${this.$store.state.longitude}`;
      } else {
        this.search = `location=${this.$store.state.currentSearch}`;
      }


      if (term !== `` && category !== ``) {
        this.search += `&term=${this.$store.state.category} ${this.$store.state.term}`;
      } else if (term !== ``) {
        this.search += `&term=${this.$store.state.term}`;
      } else if (category !== ``) {
        // Can definitely refine this later
        this.search += `&term=${this.$store.state.category}`;

      }

      this.$store.commit('SET_LOADING', true);
      RestaurantService.getRestaurants(this.search).then((response) => {
        this.currentResponse = response;
        this.updateRestaurants();
        if (this.end >= this.currentResponse.data.length) {
          this.showForward = false;
        } else {
          this.showForward = true;
        }
        this.$store.commit("TOGGLE_RESTAURANTS", true);
        this.$store.commit('SET_LOADING', false);
      });

    },
    nextPage(next) {
  if (next) {
    this.start += 3;
    this.end += 3;
  } else {
    this.start -= 3;
    this.end -= 3;
  }
  
  this.showBack = this.start > 0;

  if (this.end >= this.currentResponse.data.length) {
    this.showForward = false;
  } else {
    this.showForward = true;
  }

  this.updateRestaurants();
},
    updateRestaurants() {
      this.restaurants = [];
      // going to be needed but hold on
      for (let i = this.start; i < this.end; i++) {
        if (this.currentResponse.data[i]) {
          this.restaurants.push(this.currentResponse.data[i]);
        }
      }


    },
  },
  created() {
    this.$store.subscribe((mutation, state) => {
      if (mutation.type === 'SET_NARROW_TERM') {
        this.findEatery();
      } else if (mutation.type === 'SET_LOCATION') {
        this.findEatery();
      } else if (mutation.type === 'SET_CATEGORY') {
        this.findEatery();
      }


    });
    if (this.start === 0) {
      return false;
    } else {
      return true;
    }

  },

};
</script>

<style scoped>
.eatery-grid-container {
  display: grid;
  grid-template-columns: 1fr 9fr 1fr;
  justify-items: center;
}

button {
  background-image: url('../../../borderImage.png');
  background-size: cover;
  box-shadow: 20px 20px 40px rgba(164, 36, 115, 0.8);
}

i {
  color: #7FFF00;
  font-weight: bold;
  font-size: 4em;
}
</style>