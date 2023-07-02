import { createRouter, createWebHistory } from 'vue-router'
import KsiazkaList from "./components/KsiazkaList.vue";
import DostawaList from "@/components/DostawaList.vue";
import DostawaKsiazkaList from "@/components/DostawaKsiazkaList.vue";
import KsiazkaForm from "@/components/KsiazkaForm.vue";
import DostawaForm from "@/components/DostawaForm.vue";
import DostawaKsiazkaForm from "@/components/DostawaKsiazkaForm.vue";
import RezerwacjaList from "@/components/RezerwacjaList.vue";
import RezerwacjaForm from "@/components/RezerwacjaForm.vue";
import RezerwacjaKsiazkaList from "@/components/RezerwacjaKsiazkaList.vue";
import RezerwacjaKsiazkaForm from "@/components/RezerwacjaKsiazkaForm.vue";

const routes = [
    {
        path: '/ksiazka_list',
        name: 'Ksiazki',
        component: KsiazkaList
    },
    {
        path: '/dostawa_list',
        name: 'Dostawy',
        component: DostawaList
    },
    {
        path: '/dostawa_ksiazka_list/:id_dostawa',
        name: 'Dostawa - Książka',
        component: DostawaKsiazkaList
    },
    {
        path: '/rezerwacja_list',
        name: 'Rezerwacja',
        component: RezerwacjaList
    },
    {
        path: '/rezerwacja_ksiazka_list/:id_rezerwacja',
        name: 'Rezerwacja - Książka',
        component: RezerwacjaKsiazkaList
    },
    {
        path: '/ksiazka_edit/:id',
        name: 'Książka - Edycja',
        component: KsiazkaForm,
    },
    {
        path: '/ksiazka_edit',
        name: 'Książka - Dodawanie',
        component: KsiazkaForm,
    },
    {
        path: '/dostawa_edit/:id_dostawa',
        name: 'Dostawa - Edycja',
        component: DostawaForm,
    },
    {
        path: '/dostawa_edit',
        name: 'Dostawa - Dodawanie',
        component: DostawaForm,
    },
    {
        path: '/dostawa_ksiazka_edit/:id_dostawa/:id_ksiazka',
        name: 'Dostawa-Książka - Edycja',
        component: DostawaKsiazkaForm,
    },
    {
        path: '/dostawa_ksiazka_edit/:id_dostawa',
        name: 'Dostawa-Książka - Dodawanie',
        component: DostawaKsiazkaForm,
    },
    {
        path: '/rezerwacja_edit/:id_rezerwacja',
        name: 'Rezerwacja - Edycja',
        component: RezerwacjaForm,
    },
    {
        path: '/rezerwacja_edit',
        name: 'Rezerwacja - Dodawanie',
        component: RezerwacjaForm,
    },
    {
        path: '/rezerwacja_ksiazka_edit/:id_rezerwacja/:id_ksiazka',
        name: 'Rezerwacja-Książka - Edycja',
        component: RezerwacjaKsiazkaForm,
    },
    {
        path: '/rezerwacja_ksiazka_edit/:id_rezerwacja',
        name: 'Rezerwacja-Książka - Dodawanie',
        component: RezerwacjaKsiazkaForm,
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router