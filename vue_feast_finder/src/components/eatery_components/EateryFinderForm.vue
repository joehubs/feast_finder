<template>
    <form class="eatery-finder-form" v-on:submit.prevent="setSearch">

            <label for="zipcode">Zipcode/City:</label>
            <input type="text" v-model="zipcode" />
            <label for="term">Filter:</label>
            <input type="text" v-model="term" />
            <button type="submit">Find Feast Spots</button>
    </form>
</template>

<script>
export default {
    data() {
        return {
            zipcode: ``,
            term: ``,
        };
    },
    methods: {
        setSearch() {
            this.$store.commit('SET_SEARCH_TERM', this.zipcode);
            this.$store.commit('SET_NARROW_TERM', this.term);
            this.resetForm();
        },
        resetForm() {
            this.zipcode = "";
            this.term = "";
        },
    },
};

</script>

<style>
.eatery-finder-form {
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 1rem;
    gap: 15px;
    border: 1px solid #36454F;
    border-radius: 5px;
    background-color: #E6E6FA;
    width: 95%;
    justify-content: space-evenly;
}

.eatery-finder-form input {
    width: 100px;
}

.eatery-finder-form input:nth-child(4) {
    width: 200px;
}

</style>
