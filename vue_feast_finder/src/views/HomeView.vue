<template>
  <div class="home">
    <div v-if="showFullSearch">

      <!-- CreateGroupForm will be shown based on $store.state.showGroupForm -->
      <CreateGroupForm v-if="$store.state.showGroupForm" />

      <!-- Search form section -->
      <div v-if="showForm" class="search-form-container">
        <div class='full-search-form'>
          <EateryFinderFormVue />
          <div class="options">
            <div class="popular-search-area">
              <div v-if="seePopular" class="category-container">
                <button v-for="category in categories" :key="category" class="category"
                  @click="setCategory(category)">{{ category }}</button>
              </div>
            </div>
            <button @click="toggleSeePopular">{{ seePopular ? 'Hide Popular Searches' : 'Show Popular Quick Searches' }}</button>
            <button @click="showForm = !showForm">Hide Form</button>
          </div>
        </div>
      </div>

      <!-- Buttons for toggling group form and search form -->
      <div v-else>
        <button v-if="!$store.state.showGroupForm" v-on:click="toggleGroup">Create a New Feast</button>
        <button v-else v-on:click="toggleGroup">Hide Create Form</button>
        <button @click="showForm = !showForm">Search Filters</button>
      </div>
    </div>

    <!-- Change search button -->
    <div v-else>
      <button @click="showFullSearch = !showFullSearch" id="change-search">Change Search</button>
    </div>

    <!-- Conditionally show EateryList based on group form visibility using v-show -->
    <EateryList v-show="!$store.state.showGroupForm && restaurants.length < 2" :restaurants="restaurants" />
  </div>
</template>

<script>
import EateryFinderFormVue from "../components/eatery_components/EateryFinderForm.vue";
import EateryList from "../components/eatery_components/EateryList.vue";
import CreateGroupForm from "../components/group_components/CreateGroupForm.vue";
export default {
  data() {
    return {
      restaurants: [],
      categories: [
        "Best restaurants near me",
        "Top-rated restaurants",
        "Popular restaurants in my area",
        "Family-friendly restaurants",
        "Romantic restaurants",
        "Cheap eats near me",
        "Best places to eat",
        "Vegan restaurants near me",
        "Gluten-free restaurants",
        "Healthy restaurants",
        // Other categories can be added here
      ],
      seePopular: false,
      showForm: false,
      showFullSearch: false,
    };
  },
  components: {
    EateryFinderFormVue,
    EateryList,
    CreateGroupForm,
  },
  methods: {
    setCategory(category) {
      this.$store.commit('SET_CATEGORY', '');
      this.$store.commit("SET_CATEGORY", category);
    },
    toggleSeePopular() {
      this.seePopular = !this.seePopular;
    },
    toggleGroup() {
      this.$store.commit("TOGGLE_GROUP_FORM", !this.$store.state.showGroupForm);
    },
  },
  created() {
    navigator.geolocation.getCurrentPosition(({ coords }) => {
      this.$store.commit("SET_LOCATION", {
        latitude: coords.latitude,
        longitude: coords.longitude,
      });
    });
  }
};
</script>

<style scoped>
button {
  color: #f2fae6;
  background-color: #228B22;
  border-radius: 20px;
  padding: 15px;
  margin: 5px;
  box-shadow: 0px 0px 10px #8A2BE2;
}

.home {
  display: grid;
  justify-items: center;
  align-items: start;
}

.category-container {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 10px;
  justify-items: center;
}

.category {
  color: #e6faeb;
  font-size: 1em;
  background-color: #8A2BE2;
  min-width: 10vw;
  border-radius: 20px;
  padding: 5px;
  margin: 0;
}

.full-search-form {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
  align-items: start;
}

.popular-search-area {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
}

.search-form-container {
  display: grid;
  grid-template-columns: 1fr;
  color: #8A2BE2;
  font-size: 1.2em;
  background-color: #E6E6FA;
  border-radius: 30px;
  justify-items: center;
  padding: 15px 25px 5px 25px;
  box-shadow: 0px 0px 25px #8A2BE2;
}


.options {
  display: flex;
  flex-direction: row;
  padding: 5px;
}

#change-search {
  font-size: 1.3em;
  color: #8A2BE2;
  background-color: #E6E6FA;
  padding: 10px;
  margin: 0;
}
</style>
