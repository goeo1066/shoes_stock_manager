<script>
    import axios from "axios";
    import {onMount} from "svelte";

    /**
     * @type {{id: string, name: string, size: string, qty: number}[]} shoesList
     */
    let shoesList = [];
    let sum = 0;
    $: {
        let newSum = 0;
        for (let shoesListElement of shoesList) {
            newSum += shoesListElement.qty;
        }
        sum = newSum;
    }

    async function fetchShoes() {
        shoesList = (await axios.get('http://localhost:8080/instock')).data;
    }

    function addRow() {
        shoesList.push({id: "", name: "", size: "", qty: 1});
        shoesList = shoesList;
    }

    async function saveData() {
        await axios.put('http://localhost:8080/instock', shoesList);
        await fetchShoes();
    }

    /**
     *
     * @param {string} id
     */
    async function deleteData(id) {
        await axios.delete('http://localhost:8080/instock/' + id)
        await fetchShoes();
    }

    onMount(async () => {
        await fetchShoes();
    })
</script>

<svelte:head>
    <title>Home</title>
    <meta name="description" content="Svelte demo app"/>
</svelte:head>

<section>
    <div class="flex flex-col gap-1">
        {#if shoesList}
            {#each shoesList as shoe}
                <div class="flex flex-row gap-1">
                    <div class="w-96 hidden">{shoe.id}</div>
                    <input class="w-52" bind:value={shoe.name}/>
                    <input class="w-10 text-right" bind:value={shoe.size}/>
                    <input type="number" class="w-10 text-right" bind:value={shoe.qty}>
                    <button class="ml-5 bg-amber-600 w-6" on:click={() => deleteData(shoe.id)}>-</button>
                </div>
            {/each}
        {/if}
        <div class="h-2"></div>
        <button class="bg-amber-200 w-80" on:click={addRow}>New Row</button>
        <button class="bg-amber-200 w-80" on:click={saveData}>Save</button>
        <div>{'total: ' + sum}</div>
    </div>
</section>
