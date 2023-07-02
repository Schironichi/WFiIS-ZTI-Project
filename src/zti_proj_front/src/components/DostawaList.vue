<script>
import http from "../http-common"

export default {
  name: 'dostawa-list',
  data() {
    return {
      columns: [
        { field: 'id_dostawa', label: 'ID' },
        { field: 'data',
          label: 'Data',
          type: 'date',
          dateInputFormat: 'yyyy-MM-dd',
          dateOutputFormat: 'dd-MM-yyyy',},
        { field: 'akcje', label: 'Akcje', type: 'edit' },
      ],
      jsonData: [],
      errorMessage: '',
    };
  },
  async created() {
    await this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await http.get('dostawa');
        this.jsonData = response.data;
      } catch (error) {
        this.errorMessage = `Wystąpił błąd: ${error}`;
        console.error(error);
      }
    },
    editRow(row) {
      this.$router.push(`/dostawa_edit/${row}`);
    },
    delRow(row) {
      http.delete(`dostawa/${row}`)
          .then(response => {
            this.fetchData();
          })
          .catch(error => {
            console.error('Wystąpił błąd:', error);
            this.errorMessage = `Wystąpił błąd: ${error}`;
          });
    },
    getDostawaLink(id) {
      return `/dostawa_ksiazka_list/${id}`;
      },
  },
};
</script>

<template>
  <div>
    <div v-if="errorMessage" class="alert alert-danger">
      {{ errorMessage }}
      <br>
    </div>
    <router-link to="/dostawa_edit" class="btn btn-primary">Nowa Dostawa</router-link>
    <br><br>
    <vue-good-table class="custom-table"
        :columns="columns"
        :rows="jsonData">
      <template #table-row="props">
        <span v-if="props.column.field == 'id_dostawa'">
          <router-link :to="getDostawaLink(props.row.id_dostawa)" class="text-primary">{{props.row.id_dostawa}}</router-link>
        </span>
        <span v-else-if="props.column.field == 'akcje'">
          <button class="btn btn-primary" @click="editRow(props.row.id_dostawa)">Edytuj</button>
          <button class="btn btn-danger" @click="delRow(props.row.id_dostawa)">Usuń</button>
        </span>
      </template>
    </vue-good-table>
  </div>
</template>