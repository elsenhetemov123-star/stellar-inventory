const API = '/products';

// DOM Elements
const themeToggle = document.getElementById('theme-toggle');
const addBtn = document.getElementById('add-btn');
const cancelBtn = document.getElementById('cancel-btn');
const modalOverlay = document.getElementById('modal-overlay');
const productForm = document.getElementById('product-form');
const productTable = document.querySelector('#product-table tbody');
const totalCountEl = document.getElementById('total-count');
const totalValueEl = document.getElementById('total-value');
const modalTitle = document.getElementById('modal-title');
const loader = document.getElementById('loader');

let isEditMode = false;

// Theme Logic
themeToggle.addEventListener('click', () => {
    document.body.classList.toggle('dark');
    const isDark = document.body.classList.contains('dark');
    localStorage.setItem('theme', isDark ? 'dark' : 'light');
});

// Load saved theme
if (localStorage.getItem('theme') === 'dark') {
    document.body.classList.add('dark');
}

// Modal Logic
const showModal = (edit = false, product = null) => {
    isEditMode = edit;
    modalTitle.innerText = edit ? 'Edit Resource' : 'New Entry';
    
    if (edit && product) {
        document.getElementById('product-id').value = product.id;
        document.getElementById('name').value = product.name;
        document.getElementById('price').value = product.price;
    } else {
        productForm.reset();
        document.getElementById('product-id').value = '';
    }
    
    modalOverlay.classList.remove('hidden');
};

const hideModal = () => {
    modalOverlay.classList.add('hidden');
    productForm.reset();
};

addBtn.addEventListener('click', () => showModal(false));
cancelBtn.addEventListener('click', hideModal);
modalOverlay.addEventListener('click', (e) => {
    if (e.target === modalOverlay) hideModal();
});

// API Functions
async function fetchProducts() {
    loader.classList.remove('hidden');
    try {
        const res = await fetch(API);
        const data = await res.json();
        renderProducts(data);
    } catch (err) {
        console.error('Fetch error:', err);
    } finally {
        loader.classList.add('hidden');
    }
}

function renderProducts(products) {
    productTable.innerHTML = '';
    let totalValue = 0;

    products.forEach(p => {
        totalValue += p.price;
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td style="font-family: monospace; color: var(--text-muted);">#${p.id}</td>
            <td>${p.name}</td>
            <td style="font-weight: 700;">$${p.price.toLocaleString(undefined, {minimumFractionDigits: 2})}</td>
            <td class="text-right">
                <div class="op-btns">
                    <button class="op-btn" onclick="editProduct(${p.id})">Edit</button>
                    <button class="op-btn delete" onclick="deleteProduct(${p.id})">Delete</button>
                </div>
            </td>
        `;
        productTable.appendChild(tr);
    });

    totalCountEl.innerText = products.length;
    totalValueEl.innerText = `$${totalValue.toLocaleString(undefined, {minimumFractionDigits: 2})}`;
}

productForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('product-id').value;
    const name = document.getElementById('name').value;
    const price = parseFloat(document.getElementById('price').value);

    const productData = { name, price };
    const method = isEditMode ? 'PUT' : 'POST';
    const url = isEditMode ? `${API}/${id}` : API;

    try {
        const res = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(productData)
        });

        if (res.ok) {
            hideModal();
            fetchProducts();
        }
    } catch (err) {
        console.error('Save error:', err);
    }
});

async function editProduct(id) {
    try {
        const res = await fetch(`${API}/${id}`);
        const product = await res.json();
        showModal(true, product);
    } catch (err) {
        console.error('Load error:', err);
    }
}

async function deleteProduct(id) {
    if (!confirm('Permanent deletion. Proceed?')) return;
    try {
        const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
        if (res.ok) fetchProducts();
    } catch (err) {
        console.error('Delete error:', err);
    }
}

// Initial Load
fetchProducts();
