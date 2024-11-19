<template>
    <div id="create-group-container">
        <button v-if="$store.state.showGroupForm" v-on:click="toggleGroup">Hide Create Form</button>
        <form id="create-group-form" v-on:submit.prevent="submitGroup">
            <div class="event-container">
                <div class="sub-container">

                    <label for="group-name">
                        <h3>Feast Name:</h3>
                    </label>
                    <input type="text" id="group-name" name="groupName" v-model="newGroup.vote_name">
                </div>
            </div>
            <div class="event-container">
                <div class="sub-container">
                    <label for="group-description">
                        <h3>Description:</h3>
                    </label>
                    <input type="text" id="group-description" name="groupDescription"
                        v-model="newGroup.group_description">
                </div>
            </div>
            <div class="event-container">
                <div class="sub-container">

                    <label for="vote-date">
                        <h3>Start Vote:</h3>
                    </label>
                    <input type="date" id="vote-date" name="voteDate" v-model="newGroup.vote_date">
                </div>
            </div>
            <div class="event-container">
                <div class="sub-container">
                    <label for="event-date">
                        <h3>
                            Day of Feast:
                        </h3>
                    </label>
                    <input type="date" id="event-date" name="eventDate" v-model="newGroup.event_date">
                </div>
            </div>

            <button id="create-group-button" type="submit">Create This Feast</button>
        </form>
    </div>
</template>

<script>
import VoteService from '../../services/VoteService.js';
export default {
    data() {
        return {
            newGroup: {
                vote_name: '',
                vote_description: '',
                event_date: '',
                vote_date: '',
                is_active: true,
                eateries: [],
            }

        }

    },
    methods: {
        toggleGroup() {
            this.$store.commit("TOGGLE_GROUP_FORM", !this.$store.state.showGroupForm);
        },
        submitGroup() {
            if (this.newGroup.vote_name !== '') {
                let currentDate = new Date();
                let newEventDate = new Date(this.newGroup.event_date);
                let newVoteDate = new Date(this.newGroup.vote_date);

                VoteService.createGroup(this.newGroup)
                    .then(response => {
                        console.log(response);
                        if (response.status === 200) {
                            this.newGroup = {};
                            this.$store.commit('TOGGLE_GROUP_FORM', false);
                            console.log("Form visibility state (after mutation):", this.$store.state.showGroupForm);
                            this.$router.push('/groups');
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });

                this.resetForm();
            } else {
                alert("Please enter a name for the feast.");
            }
        },
        resetForm() {
            this.newGroup = {
                vote_name: '',
                vote_description: '',
                event_date: '',
                vote_date: '',
                is_active: true
            }
        }
    },
    created() {
        const today = new Date().toISOString().split('T')[0];
        this.newGroup.vote_date = today;

        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        this.newGroup.event_date = tomorrow.toISOString().split('T')[0];
    },
}
</script>

<style>
h3 {
    font-size: 1.2rem;
    color: #8A2BE2;
    background-color: #E6E6FA;
    padding: 1vh;
    border-radius: 10px;
}

#create-group-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    border-radius: 30px;
}

#create-group-form {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    padding: 1rem;
    gap: 15px;
    border-radius: 30px;
    background-color: #ea6190;
    max-width: 50vw;
    justify-content: space-evenly;
    box-shadow: 0px 0px 10px 5px #20d3ed;
}

.event-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-image: url('../../borderImage.png');
    border-radius: 30px;
    padding: 2vw;
    width: 15vw;
}

.sub-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #fdac2a;
    border-radius: 30px;
    padding: 20px;
}
</style>