<template>
  <div>
    <div class="card">
      <div class="card-header">
        <EateryCard :restaurants="restaurants" :upVotes="upVotes" :downVotes="downVotes" />
      </div>
      <div class="card-body">

      </div>
    </div>
  </div>
</template>

<script>
import VoteService from '../../services/VoteService.js';
import EateryCard from '../eatery_components/EateryCard.vue';
export default {
  data() {
    return {
      restaurants: [],
      upVotes: [],
      downVotes: [],
    };
  },
  props: ['group'],
  components: {
    EateryCard,
  },
  async created() {
  try {
    const response = await VoteService.getEateries(this.group.vote_id);
    this.restaurants = response.data;

    for (const group of this.restaurants) {
      try {
        const upVoteResponse = await VoteService.getVoteTrueCount(group.eatery_id);
        const downVoteResponse = await VoteService.getVoteFalseCount(group.eatery_id);

        this.upVotes.push(upVoteResponse.data);
        this.downVotes.push(downVoteResponse.data);
      } catch (voteError) {
        console.log(voteError);
      }
    }
  } catch (error) {
    console.log(error);
  }
}


}

</script>

<style></style>