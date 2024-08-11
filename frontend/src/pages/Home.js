import { Menu, MenuButton, MenuItem, MenuItems, Popover, PopoverButton, PopoverGroup, PopoverPanel } from '@headlessui/react';
import { ChevronDownIcon } from '@heroicons/react/20/solid'
import filters from '../filters';
import BookDisplay from '../components/BookDisplay';

export default function Home() {

    

    const sortOptions = [
        { name: 'Most Popular', href: '#' },
        { name: 'Best Rating', href: '#' },
        { name: 'Newest', href: '#' },
        { name: 'Price: Low to High', href: '#' },
        { name: 'Price: High to Low', href: '#' },
    ]
    
    const products1 = [
        {
            id: 1,
            name: 'Focus Paper Refill',
            href: '#',
            price: '$13',
            description: '3 sizes available',
            imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-01.jpg',
            imageAlt: 'Person using a pen to cross a task off a productivity paper card.',
        },
        {
            id: 2,
            name: 'Focus Card Holder',
            href: '#',
            price: '$64',
            description: 'Walnut',
            imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-02.jpg',
            imageAlt: 'Paper card sitting upright in walnut card holder on desk.',
        },
        {
            id: 3,
            name: 'Focus Carry Pouch',
            href: '#',
            price: '$32',
            description: 'Heather Gray',
            imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-03.jpg',
            imageAlt: 'Textured gray felt pouch for paper cards with snap button flap and elastic pen holder loop.',
        }, {
            id: 11,
            name: 'Focus Paper Refill',
            href: '#',
            price: '$13',
            description: '3 sizes available',
            imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-01.jpg',
            imageAlt: 'Person using a pen to cross a task off a productivity paper card.',
        },
        {
            id: 12,
            name: 'Focus Card Holder',
            href: '#',
            price: '$64',
            description: 'Walnut',
            imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-02.jpg',
            imageAlt: 'Paper card sitting upright in walnut card holder on desk.',
        },
        {
            id: 13,
            name: 'Focus Carry Pouch',
            href: '#',
            price: '$32',
            description: 'Heather Gray',
            imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-01-image-card-03.jpg',
            imageAlt: 'Textured gray felt pouch for paper cards with snap button flap and elastic pen holder loop.',
        },
        // More products...
    ]
    return (
        <div className="mx-auto max-w-3xl px-4 sm:px-6 lg:max-w-7xl lg:px-8">
            <div className="py-24 text-center">
                <h1 className="text-4xl font-bold tracking-tight text-gray-900">New Arrivals</h1>
                <p className="mx-auto mt-4 max-w-3xl text-base text-gray-500">
                    Thoughtfully designed objects for the workspace, home, and travel.
                </p>
            </div>

            {/* Filters */}
            <section aria-labelledby="filter-heading" className="border-t border-gray-200 pt-6">
                <h2 id="filter-heading" className="sr-only">
                    Product filters
                </h2>

                <div className="flex items-center justify-between">
                    <Menu as="div" className="relative inline-block text-left">
                        <div>
                            <MenuButton className="group inline-flex justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                                Sort
                                <ChevronDownIcon
                                    aria-hidden="true"
                                    className="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                                />
                            </MenuButton>
                        </div>

                        <MenuItems
                            transition
                            className="absolute left-0 z-10 mt-2 w-40 origin-top-left rounded-md bg-white shadow-2xl ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                        >
                            <div className="py-1">
                                {sortOptions.map((option) => (
                                    <MenuItem key={option}>
                                        <a
                                            href={option.href}
                                            className="block px-4 py-2 text-sm font-medium text-gray-900 data-[focus]:bg-gray-100"
                                        >
                                            {option.name}
                                        </a>
                                    </MenuItem>
                                ))}
                            </div>
                        </MenuItems>
                    </Menu>
{/* 
                    <button
                        type="button"
                        onClick={() => setMobileFiltersOpen(true)}
                        className="inline-block text-sm font-medium text-gray-700 hover:text-gray-900 sm:hidden"
                    >
                        Filters
                    </button>*/}

                    <PopoverGroup className="hidden sm:flex sm:items-baseline sm:space-x-8">
                        {filters.map((section, sectionIdx) => (
                            <Popover key={section.name} id="menu" className="relative inline-block text-left">
                                <div>
                                    <PopoverButton className="group inline-flex items-center justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                                        <span>{section.name}</span>
                                        {sectionIdx === 0 ? (
                                            <span className="ml-1.5 rounded bg-gray-200 px-1.5 py-0.5 text-xs font-semibold tabular-nums text-gray-700">
                                                1
                                            </span>
                                        ) : null}
                                        <ChevronDownIcon
                                            aria-hidden="true"
                                            className="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                                        />
                                    </PopoverButton>
                                </div>

                                <PopoverPanel
                                    transition
                                    className="absolute right-0 z-10 mt-2 origin-top-right rounded-md bg-white p-4 shadow-2xl ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
                                >
                                    <form className="space-y-4">
                                        {section.options.map((option, optionIdx) => (
                                            <div key={option.value} className="flex items-center">
                                                <input
                                                    defaultValue={option.value}
                                                    defaultChecked={option.checked}
                                                    id={`filter-${section.id}-${optionIdx}`}
                                                    name={`${section.id}[]`}
                                                    type="checkbox"
                                                    className="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"
                                                />
                                                <label
                                                    htmlFor={`filter-${section.id}-${optionIdx}`}
                                                    className="ml-3 whitespace-nowrap pr-6 text-sm font-medium text-gray-900"
                                                >
                                                    {option.label}
                                                </label>
                                            </div>
                                        ))}
                                    </form>
                                </PopoverPanel>
                            </Popover>
                        ))}
                    </PopoverGroup>
                </div>
            </section>

            {/* Product grid */}
            <section aria-labelledby="products-heading" className="mt-8 pb-24">
                <h2 id="products-heading" className="sr-only">
                    Books
                </h2>

                <div className="grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:gap-x-8">
                    {products1.map((book) => (
                        <BookDisplay key={book.id} book={book}></BookDisplay>
                    ))}
                </div>
            </section>


        </div>
    );
}